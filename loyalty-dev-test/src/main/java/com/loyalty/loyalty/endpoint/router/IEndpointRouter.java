package com.loyalty.loyalty.endpoint.router;

import com.loyalty.loyalty.endpoint.handler.IEndpointHandler;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface IEndpointRouter<T extends IEndpointHandler<ServerRequest, ServerResponse>, U extends ServerResponse> {
    RouterFunction<U> route(T handler);
}
