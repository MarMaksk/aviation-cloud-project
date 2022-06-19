package com.avia.gateway.config;

import com.avia.gateway.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import javax.servlet.Filter;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtUtil jwtUtil;
    @Value("Avia ")
    private String TOKEN_PREFIX;


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("flight-order", r -> r.path("/avia/order/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://flight-order"))
                .build();
    }


}
