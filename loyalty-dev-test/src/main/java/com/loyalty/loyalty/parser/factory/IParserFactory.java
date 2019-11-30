package com.loyalty.loyalty.parser.factory;

import com.loyalty.loyalty.parser.IParse;

public interface IParserFactory {
    IParse buildParser(ParserFactoryId parserId);
}
