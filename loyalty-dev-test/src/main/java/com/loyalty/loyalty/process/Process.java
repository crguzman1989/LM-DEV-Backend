package com.loyalty.loyalty.process;

import com.loyalty.loyalty.parser.factory.IParserFactory;
import com.loyalty.loyalty.parser.factory.ParserFactoryId;
import com.loyalty.loyalty.pojo.Request;
import com.loyalty.loyalty.pojo.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@AllArgsConstructor
@Service
public class Process implements IProcess<Request, Response>{

    private IParserFactory parserFactory;

    @Override
    public Mono<Response> process(Request request) {

        return Mono.just(request)
                .flatMap(infixExp -> parserFactory.buildParser(ParserFactoryId.PARSE_INFIX_TO_POSTFIX).parse(infixExp)
                        .flatMap(postFixExp -> parserFactory.buildParser(ParserFactoryId.PARSE_POSTFIX_TO_RESPONSE_POJO).parse(Tuples.of(infixExp.getExp(), postFixExp))))
                ;
    }
}
