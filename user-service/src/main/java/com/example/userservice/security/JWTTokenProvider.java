package com.example.userservice.security;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
        UserDTO user = userService.getUserById((((User) authentication.getPrincipal()).getId()));
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", user.getId());
        claimsMap.put("username", user.getUsername());
        claimsMap.put("firstname", user.getFirstname());
        claimsMap.put("lastname", user.getLastname());
        claimsMap.put("roles", user.getRoles());
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
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException ex) {
            LOG.info(ex.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        String id = claims.get("id").toString();
        return Long.parseLong(id);
    }
}
/*
Ilya Kireyeu16:03
https://www.baeldung.com/spring-boot-add-filter
Ilya Kireyeu16:08
https://stackoverflow.com/questions/47224931/is-setting-roles-in-jwt-a-best-practice
Ilya Kireyeu16:11
https://docs.docker.com/compose/startup-order/
 */
