package com.server.fiboserver.controller;

import com.server.fiboserver.service.FibonacciSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.math.BigInteger;
import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class FibonacciClientController {

    private final FibonacciSequence fibonacciSequence;

    @MessageMapping("fibonacci.sequence")
    public Flux<BigInteger> getSequenceFibonacci() {

        int timeOut = 100;

        return this.fibonacciSequence.generateFibonacciSequence()
                .delayElements(Duration.ofMillis(timeOut));

    }
}
