package com.avia.gateway.security;

import com.avia.gateway.User;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Order(2)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTAuthenticationFilter extends OncePerRequestFilter implements WebFilter {

    static Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
    private JwtUtil jwtUtil;
    @Value("Avia ")
    private String TOKEN_PREFIX;
    private String HEADER_STRING = "Authorization";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJWTFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtUtil.isInvalid(jwt)) {
                Claims allClaimsFromToken = jwtUtil.getAllClaimsFromToken(jwt);
                // Теоретически можно и не создавать юзера, а просто передавать username как объект
                User user = new User(Long.parseLong(allClaimsFromToken.get("id").toString()),
                        allClaimsFromToken.get("username").toString());
                String roles = allClaimsFromToken.get("roles").toString();
                System.out.println(roles);

//                user.getRoles().stream()
//                        .map(role -> new SimpleGrantedAuthority(role.name()))
//                        .collect(Collectors.toList())
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(user, null, user.getRoles().stream()
//                                .map(role -> new SimpleGrantedAuthority(role.name()))
//                                .collect(Collectors.toList()));
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
                // В дальнейшем я планировал переопределить метод hasAnyRole или что-нибудь такое чтобы проверять наличие прав
                // Ведь UserDetails у меня не будет, не откуда брать юзера
            }
        } catch (Exception ex) {
            LOG.error("Could not set user authentication");
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String aviaToken = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(aviaToken) && aviaToken.startsWith(TOKEN_PREFIX)) {
            return aviaToken.split(" ")[1];
        }
        return null;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

//    private final RouterValidator routerValidator;//custom route validator

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("HERE FILTER");
        System.out.println(exchange.getRequest());
        System.out.println(chain);
        ServerHttpRequest request = exchange.getRequest();

//        if (routerValidator.isSecured.test(request)) {
//            if (this.isAuthMissing(request))
//                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
//            System.out.println("WOOOORKKKK");
//            final String token = this.getAuthHeader(request);
//
//            if (jwtUtil.isInvalid(token))
//                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
//
//            this.populateRequestWithHeaders(exchange, token);
//        }
        return chain.filter(exchange);
    }

    // Чтобы работало надо убрать бин этого класса
}