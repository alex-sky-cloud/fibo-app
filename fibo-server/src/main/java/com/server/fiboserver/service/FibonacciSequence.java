package com.server.fiboserver.service;

import reactor.core.publisher.Flux;

import java.math.BigInteger;

public interface FibonacciSequence {

    Flux<BigInteger> generateFibonacciSequence();
}
