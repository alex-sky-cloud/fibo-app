package com.fibo.client.fiboclient.exception;

/**
 * This class describes <b>a mistake</b> that has occurred,
 * If It is set wrong range for <b>fibonacci sequence</b>.
 */
public class RangeSequenceFibonacciException extends RuntimeException{
    public RangeSequenceFibonacciException(String message) {
        super(message);
    }
}
