package com.vusachov.urlshortener.repository.exception;

public class HashUrlRepositoryException extends Exception {

    public HashUrlRepositoryException() {
        super("URL repository: error occurred");
    }

    public HashUrlRepositoryException(String message) {
        super(message);
    }

    public HashUrlRepositoryException(String message, Throwable prevException) {
        super(message, prevException);
    }

    public HashUrlRepositoryException(Throwable prevException) {
        super(prevException);
    }
}
