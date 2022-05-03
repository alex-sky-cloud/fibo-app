package com.fibo.client.fiboclient.controller;

import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import com.fibo.client.fiboclient.model.dto.ResultCalculateSumSequenceDto;
import com.fibo.client.fiboclient.model.mapper.RangeSequenceFibonacciMapper;
import com.fibo.client.fiboclient.service.FibonacciSumValuesService;
import com.fibo.client.fiboclient.validate.ValidateRangeFibonacciSequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("fibonacci")
public class FibonacciController {

    private final RSocketRequester requester;

    private final FibonacciSumValuesService fibonacciSumValuesService;

    private final RangeSequenceFibonacciMapper fibonacciMapper;

    private final ValidateRangeFibonacciSequence validateRangeFibonacciSequence;

    @Value("${rsocket.server.routeNameSequenceFibonacci}")
    private String routeNameSequenceFibonacci;

    @GetMapping("v1/sequence")
    Flux<BigInteger> getSequenceFibonacci() {

        return getFibonacciSequenceGenerated();
    }

    @GetMapping("v1/sequence/range/{startRange}/{endRange}")
    Mono<ResultCalculateSumSequenceDto> getSumSequenceFibonacciFromRange(
            @PathVariable BigInteger startRange,
            @PathVariable BigInteger endRange) {

        this.validateRangeFibonacciSequence
                .validateManagerGivenRange(startRange, endRange);

        Flux<BigInteger> fibonacciSequenceFlux = getFibonacciSequenceGenerated();
        RangeSequenceFibonacciDto fibonacciDto = this.fibonacciMapper
                .transformToFibonacciDto(fibonacciSequenceFlux, startRange, endRange);

        Mono<ResultCalculateSumSequenceDto> sum = this.fibonacciSumValuesService
                .getSumRangeValuesFibonacciSequence(fibonacciDto);

        return sum;
    }

    private Flux<BigInteger> getFibonacciSequenceGenerated() {

        RSocketRequester.RequestSpec route =
                this.requester.route(this.routeNameSequenceFibonacci);

        return route.retrieveFlux(BigInteger.class);
    }
}