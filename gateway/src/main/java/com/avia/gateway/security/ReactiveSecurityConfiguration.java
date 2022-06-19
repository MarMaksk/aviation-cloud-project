package com.avia.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import javax.ws.rs.HttpMethod;


@EnableWebFluxSecurity
public class ReactiveSecurityConfiguration {

    private final static String CATERING_PATH = "/avia/" + "catering/";
    private final static String ORDER_PATH = "/avia/" + "order/";

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http, AuthorizationManager auth) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it ->
                        it
                                .pathMatchers(HttpMethod.GET, ORDER_PATH + "flight", ORDER_PATH + "flight/**")
                                .access(auth.check("SYSTEM", "MANAGER", "DISPATCHER"))
                                .pathMatchers(HttpMethod.POST, ORDER_PATH + "flight")
                                .access(auth.check("SYSTEM", "MANAGER"))
                                .pathMatchers(HttpMethod.PUT, ORDER_PATH + "flight")
                                .access(auth.check("SYSTEM", "MANAGER"))
                                .pathMatchers(HttpMethod.DELETE, ORDER_PATH + "flight")
                                .access(auth.check("SYSTEM", "MANAGER"))
                                .pathMatchers(HttpMethod.GET, ORDER_PATH + "util")
                                .access(auth.check("SYSTEM", "MANAGER"))
                                .pathMatchers(HttpMethod.GET, ORDER_PATH + "examination")
                                .access(auth.check("SYSTEM", "MAINTENANCE", "MANAGER", "DISPATCHER"))
                                .pathMatchers(HttpMethod.POST, ORDER_PATH + "examination")
                                .access(auth.check("SYSTEM", "MAINTENANCE"))
                                .pathMatchers(HttpMethod.GET, ORDER_PATH + "airport", ORDER_PATH + "airport/**")
                                .access(auth.check("SYSTEM", "MAINTENANCE", "MANAGER", "DISPATCHER"))
                                .pathMatchers(HttpMethod.POST, ORDER_PATH + "airport")
                                .access(auth.check("SYSTEM"))
                                .pathMatchers(HttpMethod.GET, ORDER_PATH + "airplane", ORDER_PATH + "airplane/**")
                                .access(auth.check("SYSTEM", "MANAGER", "MAINTENANCE", "DISPATCHER"))
                                .pathMatchers(HttpMethod.GET, ORDER_PATH + "airplane", ORDER_PATH + "airplane/**")
                                .access(auth.check("SYSTEM", "MANAGER", "MAINTENANCE", "DISPATCHER"))
                                .pathMatchers(HttpMethod.GET, CATERING_PATH + "tag")
                                .access(auth.check("SYSTEM", "CATERER"))
                                .pathMatchers(HttpMethod.GET, CATERING_PATH + "product", CATERING_PATH + "product/**")
                                .access(auth.check("SYSTEM", "CATERER", "DELIVERY"))
                                .pathMatchers(HttpMethod.POST, CATERING_PATH + "product")
                                .access(auth.check("SYSTEM", "CATERER"))
                                .pathMatchers(HttpMethod.GET, CATERING_PATH + "report/caterer")
                                .access(auth.check("SYSTEM", "CATERER"))
                                .pathMatchers(HttpMethod.GET, CATERING_PATH + "report/deliver")
                                .access(auth.check("SYSTEM", "DELIVERY"))
                                .pathMatchers(HttpMethod.POST, CATERING_PATH + "order")
                                .access(auth.check("SYSTEM", "DELIVERY", "CATERER"))
                                .pathMatchers(HttpMethod.PUT, CATERING_PATH + "order")
                                .access(auth.check("SYSTEM", "CATERER"))
                                .pathMatchers(HttpMethod.DELETE, CATERING_PATH + "order")
                                .access(auth.check("SYSTEM", "CATERER"))
//                                .pathMatchers(HttpMethod.GET, "/avia/order/flight").access(auth.check("SYSTEM"))
                                .pathMatchers(HttpMethod.GET, "/avia/**").permitAll()
                                .anyExchange().permitAll()
                )
                .build();
    }


}
