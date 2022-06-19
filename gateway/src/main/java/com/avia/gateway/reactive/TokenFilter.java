package com.avia.gateway.reactive;

import com.avia.gateway.security.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TokenFilter implements WebFilter {
    public static final String HEADER_PREFIX = "Avia ";
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
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
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.replace(HEADER_PREFIX, "");
        }
        return null;
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        return authenticationManager;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(Authentication authentication) {
        UserDetails build = User.withUsername(authentication.getName())
                .authorities(authentication.getAuthorities())
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .accountLocked(false)
                .build();
        System.out.println(build);
        return (ReactiveUserDetailsService) build;
    }
}
