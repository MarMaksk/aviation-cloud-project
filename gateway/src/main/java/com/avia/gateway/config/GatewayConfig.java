package com.avia.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("flight-order", r -> r.path("/avia/order/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://flight-order"))
                .route("flight-order", r -> r.path("/avia/catering/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://flight-catering"))
                .route("flight-user", r -> r.path("/avia/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://flight-user"))
                .build();
    }


}
