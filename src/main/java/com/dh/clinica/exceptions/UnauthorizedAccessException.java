package com.dh.clinica.exceptions;

public class UnauthorizedAccessException extends Exception {

    public final String operation;

    public UnauthorizedAccessException(String message, String operation) {
        super(message);
        this.operation = operation;
    }
}
