package com.example.demo.domain.exception;

/**
 * Exception thrown when numbers are not found.
 */
public class NumbersNotFoundException extends Exception {

    /**
     * Default constructor.
     * @param msg numbers not found exception message
     */
    public NumbersNotFoundException(String msg) {
        super(msg);
    }
}
