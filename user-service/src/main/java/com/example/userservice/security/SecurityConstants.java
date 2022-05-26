package com.example.userservice.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class SecurityConstants {

    static String SIGN_UP_URLS = "/avia/auth/**";

    static String SECRET = "AVIATION%PROJECT";
    public final static String TOKEN_PREFIX = "Avia ";
    static String HEADER_STRING = "Authorization";
    static String CONTENT_TYPE = "application/json";
    static long EXPIRATION_TIME = 3600_000; // Через 1 час токен будет недействителен. А надо ли?

}
