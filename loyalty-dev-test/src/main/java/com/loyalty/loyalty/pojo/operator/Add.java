package com.loyalty.loyalty.pojo.operator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Add implements IOperator{
    private String symbol = "+";
    private Integer precedence = 3;
}
