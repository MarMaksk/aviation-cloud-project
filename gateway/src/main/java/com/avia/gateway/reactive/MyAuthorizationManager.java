package com.avia.gateway.reactive;

import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcherEntry;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public class MyAuthorizationManager implements ReactiveAuthorizationManager<ServerWebExchange> {

    public List<ServerWebExchangeMatcherEntry<ReactiveAuthorizationManager<AuthorizationContext>>> mappings;

    static Logger LOG = LoggerFactory.getLogger(MyAuthorizationManager.class);

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, ServerWebExchange exchange) {
        return Flux.fromIterable(this.mappings)
                .concatMap((mapping) -> mapping.getMatcher()
                        .matches(exchange)
                        .filter(ServerWebExchangeMatcher.MatchResult::isMatch)
                        .map(ServerWebExchangeMatcher.MatchResult::getVariables)
                        .flatMap((variables) -> {
                            LOG.debug(LogMessage.of(() -> "Checking authorization on '"
                                    + exchange.getRequest()
                                    .getPath()
                                    .pathWithinApplication() + "' using "
                                    + mapping.getEntry()).toString());
                            return mapping.getEntry()
                                    .check(authentication, new AuthorizationContext(exchange, variables));
                        }).filter(AuthorizationDecision::isGranted) /* <- this is what I needed */)
                .next()
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}