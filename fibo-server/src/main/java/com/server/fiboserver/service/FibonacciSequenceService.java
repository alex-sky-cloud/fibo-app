package com.server.fiboserver.service;

import reactor.core.publisher.Flux;

import java.math.BigInteger;

public interface FibonacciSequenceService {

    Flux<BigInteger> generateFibonacciSequence();
}
