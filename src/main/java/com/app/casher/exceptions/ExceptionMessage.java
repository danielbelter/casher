package com.app.casher.exceptions;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ExceptionMessage extends RuntimeException {
    private String exceptionMessage;
    private LocalDateTime exceptionDateTime;

    @Override
    public String getMessage() {
        return "[ " + exceptionDateTime + " ]: " + exceptionMessage;
    }
}