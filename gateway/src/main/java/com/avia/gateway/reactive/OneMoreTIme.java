package com.avia.gateway.reactive;

import com.avia.gateway.security.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class OneMoreTIme {
    private static final String HEADER_PREFIX = "Avia ";
    private final JwtUtil jwtUtil;

    public ReactiveAuthorizationManager<AuthorizationContext> check(String... roles) {
        return (authentication, object) -> {
            String token = resolveToken(object.getExchange().getRequest());

            if (StringUtils.hasText(token) && !jwtUtil.isInvalid(token)) {
                Claims allClaimsFromToken = jwtUtil.getAllClaimsFromToken(token);
                ArrayList<String> rolesAuth = allClaimsFromToken.get("roles", ArrayList.class);
                for (String role : roles) {
                    if (rolesAuth.stream().anyMatch(el -> el.equals("ROLE_" + role)))
                        return Mono.just(new AuthorizationDecision(true));
                }
            }
            return Mono.just(new AuthorizationDecision(false));
        };

    }

    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.replace(HEADER_PREFIX, "");
        }
        return null;
    }

}
