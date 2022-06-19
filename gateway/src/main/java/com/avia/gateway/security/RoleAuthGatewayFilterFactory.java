package com.avia.gateway.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<RoleAuthGatewayFilterFactory.Config> {
    @Autowired
    JwtUtil jwtUtil;

    public RoleAuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("HERE");
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!jwtUtil.hasRole(request, config.getRole())) {
                var response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            return chain.filter(exchange);
        };
    }

    @Data
    public static class Config {
        private String role;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // we need this to use shortcuts in the application.yml
        return Arrays.asList("role");
    }
}
