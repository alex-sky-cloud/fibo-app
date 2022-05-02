package com.fibo.client.fiboclient.validate;

import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Component
public class ValidateRangeFibonacciSequence {

    public void validateRangeForFibonacciSequence(RangeSequenceFibonacciDto dto) {

        Flux<BigInteger> fibonacciSequenceFlux = dto.getFibonacciSequenceFlux();
        BigInteger startRange = dto.getStartRange();
        BigInteger endRange = dto.getEndRange();

        Mono<Boolean> isStartRangeMember = fibonacciSequenceFlux
                .hasElement(startRange);

        Boolean isStartRange = isStartRangeMember.block();

        Mono<Boolean> isEndRangeMember = fibonacciSequenceFlux.hasElement(endRange);
        Boolean isEndRange = isEndRangeMember.block();

        if ( !isStartRangeMember.equals(isEndRangeMember) ){
            throw new RuntimeException("\n!!!!!!!!!!!!!----неправильный диапазон -----!!!!!!!!!!!!!!!!!!!!\n");
        }
    }
}

