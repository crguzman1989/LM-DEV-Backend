package com.loyalty.loyalty.parser;

import com.loyalty.loyalty.pojo.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class ParsePostFixToResponsePojo implements IParse<Tuple2<String, String >, Response> {

    @Override
    public Mono<Response> parse(Tuple2<String, String> object) {
        return Mono.fromCallable(() -> {
            Response response = new Response();
            response.setInfix(object.getT1());
            response.setPostfix(object.getT2());
            return response;
        });
    }
}
