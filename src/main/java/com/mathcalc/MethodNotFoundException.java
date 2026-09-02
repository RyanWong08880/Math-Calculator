package com.mathcalc;

/**
 * Exception thrown when a command is not found or invalid.
 */
public class MethodNotFoundException extends Exception {
    public MethodNotFoundException(String message) {
        super(message);
    }

    public MethodNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
