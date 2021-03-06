package com.bhavinr.SampleAPIAssignment.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DatabaseEmptyException extends RuntimeException{
    public DatabaseEmptyException(String message) {
        super(message);
    }
}
