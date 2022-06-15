package com.avia.gateway.config;

import com.avia.gateway.security.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter filter;

    // маршруты у меня сделаны в проперти на гитхабе, но перенёс сюда один для эксперементов
    // Возможно их и надо перенести сюда все...

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("flight-order", r -> r.path("/avia/order/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://flight-order"))

                .build();
    }

}
