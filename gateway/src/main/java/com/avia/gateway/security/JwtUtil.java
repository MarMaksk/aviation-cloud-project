package com.avia.gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
// Возможно нужно как-то (через feign) проверить что такой токен существует. Или не нужно
// Покрайней мере я про такое на нашёл. Видел только на spring.io для Zuul и O2Auth что там упомянулось о проверке токена
// Но там это было просто упомянуто (статья была не об этом)
// Либо не нужно т.к. у нас есть secret, который все используют
public class JwtUtil {

    // Такой же как и в константах User сервиса
    @Value("AVIATION%PROJECT%PROJECT%AVIATION")
    private String secret;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}
