package com.loyalty.loyalty.parser;

import reactor.core.publisher.Mono;

public interface IParse<T, U> {
    public Mono<U> parse(T object);
}
