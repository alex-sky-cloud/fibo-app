package com.server.fiboserver.exception;

/**
 * This class describes a mistake that occure
 */
public class LimitException extends RuntimeException{
    public LimitException(String message) {
        super(message);
    }
}
