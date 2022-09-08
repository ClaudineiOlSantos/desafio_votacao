package com.southsystem.votos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {

    public ValidationException(final String message) {
        super(HttpStatus.NOT_ACCEPTABLE, message);
    }
}