package com.lorescianatico.patterns.prototype;

public class UnsupportedPrototypeException extends RuntimeException {

    public UnsupportedPrototypeException(String message) {
        super(message);
    }

    public UnsupportedPrototypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
