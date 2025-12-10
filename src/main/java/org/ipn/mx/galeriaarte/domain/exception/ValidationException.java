package org.ipn.mx.galeriaarte.domain.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}