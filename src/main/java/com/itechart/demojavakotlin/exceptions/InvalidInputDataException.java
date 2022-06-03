package com.itechart.demojavakotlin.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InvalidInputDataException extends RuntimeException {

    @Getter
    private HttpStatus status;

    public InvalidInputDataException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
