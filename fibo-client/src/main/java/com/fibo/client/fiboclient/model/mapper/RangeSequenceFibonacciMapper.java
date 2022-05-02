package com.fibo.client.fiboclient.model.mapper;

import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigInteger;

@Component
public class RangeSequenceFibonacciMapper {

    public RangeSequenceFibonacciDto transformToFibonacciDto(Flux<BigInteger> fibonacciSequenceFlux,
                                                             BigInteger startRange,
                                                             BigInteger endRange){

       return RangeSequenceFibonacciDto
                .builder()
                .startRange(startRange)
                .endRange(endRange)
                .fibonacciSequenceFlux(fibonacciSequenceFlux)
                .build();
    }
}
