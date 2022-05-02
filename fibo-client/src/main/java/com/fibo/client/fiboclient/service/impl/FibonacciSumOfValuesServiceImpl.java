package com.fibo.client.fiboclient.service.impl;

import com.fibo.client.fiboclient.cache.CacheSumValues;
import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import com.fibo.client.fiboclient.service.FibonacciSumValuesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.Map;

/**
 * This class describes calculating sum of values
 * from fibonacci sequence
 */
@Service
@RequiredArgsConstructor
public class FibonacciSumOfValuesServiceImpl implements FibonacciSumValuesService {

    private final CacheSumValues cacheSumValues;

    @Override
    public Mono<BigInteger> getSumRangeValuesFibonacciSequence(RangeSequenceFibonacciDto rangeSequenceFibonacci) {

        Mono<BigInteger> sumFromRangeValuesFibonacciSequence =
                calculateSumRangeValuesFibonacciSequence(rangeSequenceFibonacci);

        Map<String, Integer> cache = cacheSumValues.getCache();
      //для кэша использовать неблокирующую очередь

        return sumFromRangeValuesFibonacciSequence;
    }


    private Mono<BigInteger> calculateSumRangeValuesFibonacciSequence(RangeSequenceFibonacciDto rangeSequenceFibonacci){

        Flux<BigInteger> fibonacciSequenceFlux = rangeSequenceFibonacci.getFibonacciSequenceFlux();
        BigInteger startRange = rangeSequenceFibonacci.getStartRange();
        BigInteger endRange = rangeSequenceFibonacci.getEndRange();

        return fibonacciSequenceFlux
                .filter(memberFibonacci -> memberFibonacci.compareTo(startRange) >= 0)
                .filter(memberFibonacci -> memberFibonacci.compareTo(endRange) <= 0)
                .reduce(BigInteger.ZERO, BigInteger::add);

    }


}
