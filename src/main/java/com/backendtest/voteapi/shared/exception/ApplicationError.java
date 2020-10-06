package com.backendtest.voteapi.shared.exception;

import org.springframework.http.HttpStatus;

public class ApplicationError {

    private HttpStatus status;
    private String message;

    public ApplicationError(HttpStatus status, String message, Long existingId) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "ApplicationError(status=" + this.getStatus() + ", message=" + this.getMessage() + ")";
    }

}
