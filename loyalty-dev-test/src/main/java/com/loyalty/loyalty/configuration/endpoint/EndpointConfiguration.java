package com.loyalty.loyalty.configuration.endpoint;

import com.loyalty.loyalty.endpoint.handler.EndPointHandler;
import com.loyalty.loyalty.endpoint.router.EndPointRouter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
public class EndpointConfiguration {

    private Environment env;
    private EndPointHandler endPointHandler;

    @Bean
    public RouterFunction<ServerResponse> EndPointRouter(){
        return new EndPointRouter("/evaluate")
                .route(endPointHandler);
    }
}
