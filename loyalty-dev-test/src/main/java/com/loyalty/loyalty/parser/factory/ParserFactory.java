package com.loyalty.loyalty.parser.factory;

import com.loyalty.loyalty.parser.IParse;
import com.loyalty.loyalty.parser.ParseInfixToPostFix;
import com.loyalty.loyalty.parser.ParsePostFixToResponsePojo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParserFactory implements IParserFactory {

    private ParseInfixToPostFix parseInfixToPostFix;
    private ParsePostFixToResponsePojo parsePostFixToResponsePojo;

    @Override
    public IParse buildParser(ParserFactoryId parserId) {
        switch (parserId) {
            case PARSE_INFIX_TO_POSTFIX:
                return parseInfixToPostFix;
            case PARSE_POSTFIX_TO_RESPONSE_POJO:
                return parsePostFixToResponsePojo;
            default:
                return null;
        }
    }
}
