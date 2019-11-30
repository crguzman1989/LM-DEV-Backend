package com.loyalty.loyalty.endpoint.router;

import com.loyalty.loyalty.endpoint.handler.IEndpointHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.server.*;

@AllArgsConstructor
public class EndPointRouter implements IEndpointRouter<IEndpointHandler<ServerRequest, ServerResponse>, ServerResponse> {

    private String path;

    @Override
    public RouterFunction<ServerResponse> route(IEndpointHandler<ServerRequest, ServerResponse> handler) {
        return RouterFunctions
                .route(RequestPredicates.POST(this.path), handler::execute);
    }

}
