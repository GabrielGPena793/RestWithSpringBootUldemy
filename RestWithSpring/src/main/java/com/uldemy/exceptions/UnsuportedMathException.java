package com.uldemy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathException extends RuntimeException{

    public UnsuportedMathException(String message) {
        super(message);
    }
}
