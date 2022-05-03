package com.fibo.client.fiboclient.service.impl;

import com.fibo.client.fiboclient.cache.CacheSumValues;
import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import com.fibo.client.fiboclient.model.dto.ResultCalculateSumSequenceDto;
import com.fibo.client.fiboclient.service.FibonacciSumValuesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * This class describes calculating sum of values
 * from fibonacci sequence
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FibonacciSumOfValuesServiceImpl implements FibonacciSumValuesService {

    private final CacheSumValues cacheSumValues;

    @Override
    public Mono<ResultCalculateSumSequenceDto>
    getSumRangeValuesFibonacciSequence(RangeSequenceFibonacciDto rangeSequenceFibonacci) {

      //  Map<String, Integer> cache = cacheSumValues.getCache();
        //для кэша использовать неблокирующую очередь

        return buildResult(rangeSequenceFibonacci);
    }

    private Mono<ResultCalculateSumSequenceDto> buildResult(RangeSequenceFibonacciDto rangeSequenceFibonacci) {

        Mono<BigInteger> sumFromRangeValuesFibonacciSequence =
                calculateSumRangeValuesFibonacciSequence(rangeSequenceFibonacci);

        BigInteger bigInteger = null;
        try {
            bigInteger = sumFromRangeValuesFibonacciSequence.toFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }


        BigInteger[] rangeGiven = new BigInteger[]
                {rangeSequenceFibonacci.getStartRange(),
                        rangeSequenceFibonacci.getEndRange()
                };

       return Mono.just(ResultCalculateSumSequenceDto.builder()
                .sumSequence(bigInteger)
                .sequenceRange(rangeGiven)
                .isCached(false)
                .build()
        );
    }

    private Mono<BigInteger> calculateSumRangeValuesFibonacciSequence(RangeSequenceFibonacciDto rangeSequenceFibonacci) {

        Flux<BigInteger> fibonacciSequenceFlux = rangeSequenceFibonacci.getFibonacciSequenceFlux();
        BigInteger startRange = rangeSequenceFibonacci.getStartRange();
        BigInteger endRange = rangeSequenceFibonacci.getEndRange();

        return fibonacciSequenceFlux
                .filter(memberFibonacci -> memberFibonacci.compareTo(startRange) >= 0)
                .filter(memberFibonacci -> memberFibonacci.compareTo(endRange) <= 0)
                .reduce(BigInteger.ZERO, BigInteger::add);

    }
}
