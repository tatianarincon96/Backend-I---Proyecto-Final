package com.dh.clinica.exceptions;

public class ServiceException extends Exception {
    public final String operation;

    public ServiceException(String message, String operation) {
        super(message);
        this.operation = operation;
    }
}
