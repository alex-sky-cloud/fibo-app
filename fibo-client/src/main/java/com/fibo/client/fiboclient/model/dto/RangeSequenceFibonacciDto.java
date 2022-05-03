package com.fibo.client.fiboclient.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
