package org.ipn.mx.galeriaarte.domain.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entityName, Integer id) {
        super(String.format("%s con ID %d no encontrado", entityName, id));
    }
}