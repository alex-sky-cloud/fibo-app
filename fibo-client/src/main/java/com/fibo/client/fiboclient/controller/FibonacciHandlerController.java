package com.fibo.client.fiboclient.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigInteger;

@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("fibonacci")
public class FibonacciHandlerController {

    private final RSocketRequester requester;

    @Value("${routeNameSequenceFibonacci}")
    private final String routeNameSequenceFibonacci;

    @GetMapping("sequence")
    Flux<BigInteger> getSequenceFibonacci(){


        RSocketRequester.RequestSpec route =
                requester.route(this.routeNameSequenceFibonacci);

        return route.retrieveFlux(BigInteger.class);
    }

}
