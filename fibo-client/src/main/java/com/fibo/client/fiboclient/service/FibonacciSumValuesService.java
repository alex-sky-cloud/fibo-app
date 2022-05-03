package com.fibo.client.fiboclient.service;

import com.fibo.client.fiboclient.model.dto.RangeSequenceFibonacciDto;
import com.fibo.client.fiboclient.model.dto.ResultCalculateSumSequenceDto;
import reactor.core.publisher.Mono;

public interface FibonacciSumValuesService {

    Mono<ResultCalculateSumSequenceDto> getSumRangeValuesFibonacciSequence(RangeSequenceFibonacciDto rangeSequenceFibonacci);
}
