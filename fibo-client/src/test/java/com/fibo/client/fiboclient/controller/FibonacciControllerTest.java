package com.fibo.client.fiboclient.controller;

import com.fibo.client.fiboclient.model.dto.ResultCalculateSumSequenceDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigInteger;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
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

    @Value("${nameRouteSumFibonacciSequenceFromRange}")
    private String nameRouteSumFibonacciSequenceFromRange;

    private BigInteger[] fibonacciSequence;

    @BeforeEach
    public void setUp() {

        int safeTimeout = 10_000;

        webClient = webClient.mutate()
                .responseTimeout(Duration.ofMillis(safeTimeout))
                .build();

        fibonacciSequence = generateBigFibonacciSequence();
    }


    @Test
    @DisplayName("Should get all 'Fibonacci sequence'")
    void getSequenceFibonacci() {

        webClient
                .get()
                .uri(nameRouteFibonacciSequenceAll)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBodyList(BigInteger.class)
                .hasSize(sizeFibonacciSequence)
                .contains(fibonacciSequence);

    }

    private BigInteger[] generateBigFibonacciSequence() {

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
                .limit(this.sizeFibonacciSequence)
                .map(arrFibonacci -> arrFibonacci[firstIndexFibonacciMember])
                .toArray(BigInteger[]::new);
    }

    @Test
    @DisplayName("Should get sum for members 'Fibonacci sequence' from given range")
    void getSumSequenceFibonacciFromRange() {

        String startRangeSequence = "21";
        String endRangeSequence = "34";

        BigInteger startRangeFibonacci = new BigInteger(startRangeSequence);
        BigInteger endRangeFibonacci = new BigInteger(endRangeSequence);
        BigInteger expectedSum =
                calculateSumValuesFromRangeFibonacciSequence(startRangeFibonacci, endRangeFibonacci);

        ResultCalculateSumSequenceDto responseBody = webClient
                .get()
                .uri(this.nameRouteSumFibonacciSequenceFromRange, startRangeSequence, endRangeSequence)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON_VALUE)
                .expectBody(ResultCalculateSumSequenceDto.class)
                .returnResult()
                .getResponseBody();

        BigInteger sumSequenceActual = responseBody.getSumSequence();
        Assertions.assertEquals(expectedSum, sumSequenceActual);

        BigInteger[] sequenceRangeActual = responseBody.getSequenceRange();
        BigInteger[] sequenceRangeExpected = new BigInteger[]{
                startRangeFibonacci, endRangeFibonacci
        };

       Assertions.assertArrayEquals(sequenceRangeExpected, sequenceRangeActual);

    }

    private BigInteger calculateSumValuesFromRangeFibonacciSequence(BigInteger startRange,
                                                                    BigInteger finishRange) {

        List<BigInteger> fibonacciSequenceBigSize =
                Arrays
                        .stream(generateBigFibonacciSequence())
                        .collect(toList());

        int comparisonStandard = 0;

        return fibonacciSequenceBigSize
                .stream()
                .filter(memberFibonacci ->
                        memberFibonacci.compareTo(startRange) >= comparisonStandard)
                .filter(memberFibonacci ->
                        memberFibonacci.compareTo(finishRange) <= comparisonStandard)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}