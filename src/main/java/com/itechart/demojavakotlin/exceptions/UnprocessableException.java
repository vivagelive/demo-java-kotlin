package com.itechart.demojavakotlin.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UnprocessableException extends RuntimeException {

    @Getter
    private final HttpStatus status;

    public UnprocessableException(final String message) {
        super(message);
        this.status = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
