package com.vusachov.urlshortener.repository.exception;

public class URLRepositoryException extends Exception {

    public URLRepositoryException() {
        super("URL repository: error occurred");
    }

    public URLRepositoryException(String message) {
        super(message);
    }

    public URLRepositoryException(String message, Throwable prevException) {
        super(message, prevException);
    }

    public URLRepositoryException(Throwable prevException) {
        super(prevException);
    }
}
