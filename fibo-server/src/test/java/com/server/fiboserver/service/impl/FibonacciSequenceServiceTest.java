package com.server.fiboserver.service.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibonacciSequenceServiceTest {

    @Value("${upperLimitForGenerate}")
    private long upperLimitForGenerate;

    @Value("${rsocket.server.host}")
    private String localhost;

    @Value("${rsocket.server.port}")
    private Integer serverPort;

    @Value("${nameRouteEndpoint}")
    private String nameRouteEndpoint;

    @Autowired RSocketRequester.Builder builder;

    private static RSocketRequester requester;

    private List<BigInteger> fibonacciSequence;

    @BeforeAll
    public void setupOnce() {
        requester = builder
                .tcp(this.localhost, this.serverPort);

        fibonacciSequence = generateBigFibonacciSequence();
    }


    @Test
    void generateFibonacciSequence() {

        RSocketRequester.RequestSpec route =
                requester.route(this.nameRouteEndpoint);

        Flux<BigInteger> bigIntegerFlux = route.retrieveFlux(BigInteger.class);

        AtomicInteger indexGenerate = new AtomicInteger(0);

        StepVerifier
                .create(bigIntegerFlux)
                .expectNextMatches(memberFibonacci -> {
                            BigInteger memberFibonacciExpected = fibonacciSequence.get(indexGenerate.getAndIncrement());
                            return memberFibonacci.equals(memberFibonacciExpected);
                        }
                )
                .expectNextMatches(memberFibonacci -> {
                            BigInteger memberFibonacciExpected = fibonacciSequence.get(indexGenerate.getAndIncrement());
                            return memberFibonacci.equals(memberFibonacciExpected);
                        }
                )
                .expectNextMatches(memberFibonacci -> {
                            BigInteger memberFibonacciExpected = fibonacciSequence.get(indexGenerate.getAndIncrement());
                            return memberFibonacci.equals(memberFibonacciExpected);
                        }
                )
                .expectNextMatches(memberFibonacci -> {
                            BigInteger memberFibonacciExpected = fibonacciSequence.get(indexGenerate.getAndIncrement());
                            return memberFibonacci.equals(memberFibonacciExpected);
                        }
                )
                .verifyComplete();
    }


    private List<BigInteger> generateBigFibonacciSequence() {

        int firstIndexFibonacciMember = 0;
        int secondIndexFibonacciMember = 1;

        return Stream.iterate(
                        new BigInteger[]{
                                BigInteger.ZERO, BigInteger.ONE
                        },
                        arr -> new BigInteger[]{
                                arr[secondIndexFibonacciMember],
                                arr[firstIndexFibonacciMember]
                                        .add(arr[secondIndexFibonacciMember])/*add() - это операция сложения*/
                        }
                )
                .limit(this.upperLimitForGenerate)
                .map(arrFibonacci -> arrFibonacci[firstIndexFibonacciMember])
                .collect(Collectors.toList());
    }

    @AfterAll
    public static void cleanResources() {
        requester.dispose();
    }
}