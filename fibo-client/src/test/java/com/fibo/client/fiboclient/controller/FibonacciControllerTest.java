package com.fibo.client.fiboclient.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigInteger;
import java.time.Duration;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibonacciControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Value("${size.fibonacci.sequence}")
    private Integer sizeFibonacciSequence;

    @Value("${nameRouteFibonacciSequenceAll}")
    private String nameRouteFibonacciSequenceAll;

    private BigInteger [] fibonacciSequence;

    @BeforeEach
    public void setUp() {

        int safeTimeout = 30_000;

        webClient = webClient.mutate()
                .responseTimeout(Duration.ofMillis(safeTimeout))
                .build();

        fibonacciSequence = generateBigFibonacciSequence();
    }


    @Test
    @DisplayName("Should get all 'Fibonacci sequence'")
    void getSequenceFibonacci() {

        webClient.get().uri(nameRouteFibonacciSequenceAll)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBodyList(BigInteger.class)
                .hasSize(sizeFibonacciSequence)
                .contains(fibonacciSequence);

    }

    private BigInteger [] generateBigFibonacciSequence() {

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
                .limit(this.sizeFibonacciSequence)
                .map(arrFibonacci -> arrFibonacci[firstIndexFibonacciMember])
                .toArray(BigInteger[] :: new);
    }
}