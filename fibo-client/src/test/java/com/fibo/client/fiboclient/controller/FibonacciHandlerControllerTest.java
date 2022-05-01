package com.fibo.client.fiboclient.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FibonacciHandlerControllerTest {

    @Autowired
    private RSocketRequester rSocketRequester;

    @Test
    void getSequenceFibonacci() {
    }
}