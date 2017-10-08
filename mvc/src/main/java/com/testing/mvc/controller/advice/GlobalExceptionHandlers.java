package com.testing.mvc.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandlers {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String handleUnknownException() {
        return "error";
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException() {
        return "NullPointerException";
    }
}
