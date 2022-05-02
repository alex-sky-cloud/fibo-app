package com.fibo.client.fiboclient.model.dto;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.math.BigInteger;

@Data
@Builder
public class RangeSequenceFibonacciDto {

    private final BigInteger startRange;

    private final BigInteger endRange;

    private final Flux<BigInteger> fibonacciSequenceFlux;

}
