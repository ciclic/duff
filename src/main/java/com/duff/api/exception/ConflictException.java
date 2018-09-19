package com.duff.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class ConflictException extends RuntimeException implements Supplier<ConflictException> {

    public ConflictException() {
        super(CONFLICT.getReasonPhrase());
    }

    public ConflictException(String message) {
        super(message);
    }


    @Override
    public ConflictException get() {
        return this;
    }
}
