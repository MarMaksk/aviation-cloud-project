package com.avia.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/avia/auth/register",
            "/avia/auth/login"
//            "avia/order/**" //Пытался открыть этот путь для всех, но всё равно в postman получаю 401 не авторизован. А в браузере страницу для авторизации
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}