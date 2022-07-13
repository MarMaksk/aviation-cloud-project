package org.aviation.projects.userservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.userservice.dto.UserDTO;
import org.aviation.projects.userservice.entity.User;
import org.aviation.projects.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JWTTokenProvider {
    static Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);
    private final static SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.SECRET.getBytes());
    private final UserService userService;

    public String generateToken(Authentication authentication) {
        LOG.info("generateToken method called in JWTTokenProvider");
        UserDTO user = userService.getUserById((((User) authentication.getPrincipal()).getId()));
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", user.getId());
        claimsMap.put("username", user.getUsername());
        claimsMap.put("firstname", user.getFirstname());
        claimsMap.put("lastname", user.getLastname());
        claimsMap.put("roles", user.getRoles());
        LOG.info("generateToken method called in JWTTokenProvider with claimsMap: {}", claimsMap);
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .addClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            LOG.info("validateToken method called in JWTTokenProvider");
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException ex) {
            LOG.info("validateToken method called in JWTTokenProvider with exception: {}", ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        LOG.info("getUserIdFromToken method called in JWTTokenProvider");
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String id = claims.get("id").toString();
        return Long.parseLong(id);
    }
}

