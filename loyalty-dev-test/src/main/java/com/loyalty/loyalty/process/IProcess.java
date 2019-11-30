package com.loyalty.loyalty.process;

import reactor.core.publisher.Mono;

public interface IProcess<T, V> {
    Mono<V> process(T request);
}
