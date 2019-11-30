package com.loyalty.loyalty.endpoint.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IEndpointHandler<T extends ServerRequest, U extends ServerResponse> {
    Mono<U> execute(T request);
}