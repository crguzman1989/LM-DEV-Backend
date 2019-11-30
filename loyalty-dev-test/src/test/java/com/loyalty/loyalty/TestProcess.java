package com.loyalty.loyalty;

import com.loyalty.loyalty.parser.ParseInfixToPostFix;
import com.loyalty.loyalty.parser.ParsePostFixToResponsePojo;
import com.loyalty.loyalty.parser.factory.IParserFactory;
import com.loyalty.loyalty.parser.factory.ParserFactory;
import com.loyalty.loyalty.pojo.Request;
import com.loyalty.loyalty.pojo.Response;
import com.loyalty.loyalty.pojo.operator.*;
import com.loyalty.loyalty.process.IProcess;
import com.loyalty.loyalty.process.Process;
import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

public class TestProcess {

    private IParserFactory parserFactory;
    private List<IOperator> operators;
    private Request request;

    @Before
    public void setup(){

        this.request = this.createRequest();

        this.operators = new ArrayList<>();
        operators.add(new Add());
        operators.add(new Subtract());
        operators.add(new Multiply());
        operators.add(new Divide());

        this.parserFactory = new ParserFactory(
                new ParseInfixToPostFix(operators),
                new ParsePostFixToResponsePojo()
        );
    }

    @Test
    public void testProcess() {

        IProcess<Request, Response> process = new Process(parserFactory);

        StepVerifier.create(process.process(this.request))
                .expectNextMatches(response -> response.getInfix().equalsIgnoreCase("1 + 2.5 / 3 * 4")
                        && response.getPostfix().equalsIgnoreCase("1 2.5 3 / 4 * + "))
                .verifyComplete();
    }


    private Request createRequest(){
        Request request = new Request();
        request.setExp("1 + 2.5 / 3 * 4");
        return request;
    }

}
