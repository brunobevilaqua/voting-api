package com.backendtest.voteapi.shared.exception;

public class EntityNotUpdateableException extends RuntimeException {
    public EntityNotUpdateableException(String message) {
        super(message);
    }
}
