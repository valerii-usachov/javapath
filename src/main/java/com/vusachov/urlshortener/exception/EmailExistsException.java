package com.vusachov.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailExistsException extends RuntimeException {

    public EmailExistsException() {
        super("Account with this email address already exists");
    }

    public EmailExistsException(String message) {
        super(message);
    }

}