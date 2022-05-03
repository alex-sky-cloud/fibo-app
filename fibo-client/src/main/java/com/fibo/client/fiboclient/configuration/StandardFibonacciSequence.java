package com.fibo.client.fiboclient.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class StandardFibonacciSequence {

    @Value("${size.fibonacci.sequence}")
    private Long upperLimitForGenerate;

    public  List<BigInteger> generateBigFibonacciSequence() {

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
                .collect(Collectors.toList());
    }
}
