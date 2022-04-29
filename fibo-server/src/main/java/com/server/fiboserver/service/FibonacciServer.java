package com.server.fiboserver.service;

import com.server.fiboserver.exception.LimitException;

import java.util.List;

public interface FibonacciServer {

    List<Integer> generateFibonacciSequence() throws LimitException;
}
