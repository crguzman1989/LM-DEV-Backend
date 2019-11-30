package com.loyalty.loyalty.parser;

import com.loyalty.loyalty.pojo.Request;
import com.loyalty.loyalty.pojo.operator.IOperator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class ParseInfixToPostFix implements IParse<Request,String> {

    private List<IOperator> operators;

    @Override
    public Mono<String> parse(Request object) {

        return Mono.fromCallable(() -> {

            Deque<IOperator> stack = new LinkedList<>();
            StringBuilder output = new StringBuilder();

            for (String token : object.getExp().split(" ")) {

                if (operators.stream().anyMatch(operators -> token.equalsIgnoreCase(operators.getSymbol()))) {
                    for (IOperator operator : operators) {
                        if (operator.getSymbol().equalsIgnoreCase(token)) {
                            Integer precende = operator.getPrecedence();
                            while (!stack.isEmpty() && precende >= stack.peek().getPrecedence())
                                output.append(stack.pop().getSymbol()).append(' ');
                            stack.push(operator);
                        }
                    }
                } else {
                    output.append(token).append(' ');
                }
            }

            while (!stack.isEmpty()) {
                output.append(stack.pop().getSymbol()).append(' ');
            }

            return output.toString();
        });
    }
}
