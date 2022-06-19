package com.avia.gateway.reactive;

import com.avia.gateway.security.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GatewayTokenFilter implements GatewayFilter {


//    private final RouterValidator routerValidator;//custom route validator

    private final JwtUtil jwtUtil;
    @Value("Avia ")
    private String TOKEN_PREFIX;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("NOW");
        String token = resolveToken(exchange.getRequest());

        if (StringUtils.hasText(token) && this.jwtUtil.isInvalid(token)) {
            Claims allClaimsFromToken = jwtUtil.getAllClaimsFromToken(token);
            ArrayList<String> roles = allClaimsFromToken.get("roles", ArrayList.class);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(allClaimsFromToken.get("username"), null, roles
                            .stream().map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList()));
            System.out.println(roles);
            return chain.filter(exchange).subscriberContext(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }

    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.replace(TOKEN_PREFIX, "");
        }
        return null;
    }


}
