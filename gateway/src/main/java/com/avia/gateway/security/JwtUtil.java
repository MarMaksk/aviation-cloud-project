package com.avia.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
@RefreshScope
public class JwtUtil {

    @Value("%AVIATION%PROJECT%AVIATION%PROJECT%")
    private String secret;

    private Key key;

    @Value("${token.prefix}")
    private String TOKEN_PREFIX;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

    public boolean hasRole(ServerHttpRequest request, String role) {
        String token = getAuthHeader(request);
        Claims claimsFromToken = getAllClaimsFromToken(token);
        if (isInvalid(token))
            return false;
        String roles = claimsFromToken.get("roles").toString();
        return roles.contains(role);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).replace(TOKEN_PREFIX, "");
    }
}
