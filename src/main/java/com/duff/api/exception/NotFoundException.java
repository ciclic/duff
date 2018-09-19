package com.duff.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends RuntimeException implements Supplier<NotFoundException> {

    public NotFoundException() {
        super(NOT_FOUND.getReasonPhrase());
    }

    public NotFoundException(String message) {
        super(message);
    }


    @Override
    public NotFoundException get() {
        return this;
    }
}
