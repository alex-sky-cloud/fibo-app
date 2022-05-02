package com.fibo.client.fiboclient.service;

import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface FibonacciSumValuesService {

    Mono<BigInteger> getSumRangeValuesFibonacciSequence(RangeSequenceFibonacciDto rangeSequenceFibonacci);
}
