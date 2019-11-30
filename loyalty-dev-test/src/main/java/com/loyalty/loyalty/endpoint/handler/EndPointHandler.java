package com.loyalty.loyalty.endpoint.handler;

import com.loyalty.loyalty.pojo.Request;
import com.loyalty.loyalty.pojo.Response;
import com.loyalty.loyalty.process.IProcess;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@AllArgsConstructor
public class EndPointHandler implements IEndpointHandler<ServerRequest, ServerResponse> {

    IProcess<Request, Response> process;

    @Override
    public Mono<ServerResponse> execute(ServerRequest request) {
        return request.bodyToMono(Request.class)
                .flatMap(requestToProcess -> process.process(requestToProcess)
                        .flatMap(response -> ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .syncBody(response)))
                .onErrorResume(Exception.class, error -> ServerResponse.
                        status(500).
                        syncBody(new Response()));
    }
}
