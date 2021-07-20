package com.herms.hermscoder.exception;

import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;

public class OutdatedTokenException extends ServletException {
    private HttpStatus httpStatus;

    private static final String DEFAULT_MESSAGE = "Token outdated! Please login again!";

    public OutdatedTokenException() {
        super(DEFAULT_MESSAGE);
        this.httpStatus = HttpStatus.UNAUTHORIZED;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
