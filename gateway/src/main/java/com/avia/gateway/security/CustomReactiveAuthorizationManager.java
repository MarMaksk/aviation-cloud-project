package com.avia.gateway.security;

import com.avia.gateway.reactive.MyAuthorizationManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext>{
    private static final String HEADER_PREFIX = "Avia ";
    private JwtUtil jwtUtil;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        String s = resolveToken(object.getExchange().getRequest());
        System.out.println(s);
        return Mono.empty();
    }

    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.replace(HEADER_PREFIX, "");
        }
        return null;
    }
}
