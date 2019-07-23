package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.repository.InMemoryURLRepository;
import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;

public class StorageService {

    private URLRepository repository;

    public StorageService() {
        this(new InMemoryURLRepository());
    }

    public StorageService(URLRepository repository) {
        this.repository = repository;
    }

    public void put(String hash, String url) {
        try {
            repository.save(hash, url);
        } catch (URLRepositoryException e) {
            //TODO log instead print
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public String get(String hash) {
        try {
            return repository.get(hash);
        } catch (URLRepositoryException e) {
            //TODO log instead print
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    public boolean delete(String hash) {
        try {
            return repository.delete(hash);
        } catch (URLRepositoryException e) {
            //TODO log instead print
            System.out.println("Error occurred: " + e.getMessage());
        }

        return false;
    }
}
