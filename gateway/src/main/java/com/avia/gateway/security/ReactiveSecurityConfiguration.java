package com.avia.gateway.security;

import com.avia.gateway.reactive.MyAuthorizationManager;
import com.avia.gateway.reactive.OneMoreTIme;
import com.avia.gateway.reactive.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.cloud.gateway.filter.WeightCalculatorWebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterChainProxy;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.StringUtils;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.core.publisher.Mono;

import javax.ws.rs.HttpMethod;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@EnableWebFluxSecurity
public class ReactiveSecurityConfiguration {

    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER", "ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    /**
     * For Spring Security webflux, a chain of filters will provide user authentication
     * and authorization, we add custom filters to enable JWT token approach.
     *
     * @param http An initial object to build common filter scenarios.
     *             Customized filters are added here.
     * @return SecurityWebFilterChain A filter chain for web exchanges that will
     * provide security
     **/
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//
////        http.addFilterBefore(jwtAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
//        http
//                .httpBasic().and().formLogin().disable()
//                .authorizeExchange()
////                .addFilterAt(basicAuthenticationFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
//                .pathMatchers("/**")
//                .hasRole("ROLE_SYSTEM");
////                .and()
////                .addFilterAt(bearerAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
//
//        return http.build();
//    }

//    @Bean
//    public CustomAuthenticationFilter jwtAuthenticationFilter() {
//        return new CustomAuthenticationFilter();
//    }


    public static final String HEADER_PREFIX = "Avia ";
    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http, JwtUtil jwtUtil, OneMoreTIme auth) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it ->
//                        it.pathMatchers(HttpMethod.GET, "/**").hasAnyRole("SYSTEM")
                                it.pathMatchers(HttpMethod.GET, "/avia/order/flight").access(auth.check("SYSTEM"))
                                .anyExchange().permitAll()
                )
                .addFilterAt(new TokenFilter(jwtUtil), SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterBefore(new TokenFilter(jwtUtil), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.replace(HEADER_PREFIX, "");
        }
        return null;
    }

}
