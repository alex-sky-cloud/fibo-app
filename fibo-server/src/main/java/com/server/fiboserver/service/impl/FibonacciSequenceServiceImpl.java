package com.server.fiboserver.service.impl;

import com.server.fiboserver.service.FibonacciSequenceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FibonacciSequenceServiceImpl implements FibonacciSequenceService {

    @Value("${upperLimitForGenerate}")
    private Long upperLimitForGenerate;

    @Override
    public Flux<BigInteger> generateFibonacciSequence(){

        List<BigInteger> bigFibonacciSequenceList = generateBigFibonacciSequence(this.upperLimitForGenerate);

        return  Flux.fromIterable(bigFibonacciSequenceList);
    }


    private  List<BigInteger> generateBigFibonacciSequence(long upperLimitForGenerate) {

        int firstIndexFibonacciMember = 0;
        int secondIndexFibonacciMember = 1;

        return Stream.iterate(
                        new BigInteger[]{
                                BigInteger.ZERO, BigInteger.ONE
                        },
                        arr -> new BigInteger[]{
                                arr[secondIndexFibonacciMember],
                                arr[firstIndexFibonacciMember]
                                        .add(arr[secondIndexFibonacciMember])
                        }
                )
                .limit(upperLimitForGenerate)
                .map(arrFibonacci -> arrFibonacci[firstIndexFibonacciMember])
                .toList();
    }

}
