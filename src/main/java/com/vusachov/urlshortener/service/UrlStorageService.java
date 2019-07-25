package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("urlStorageService")
public class UrlStorageService implements StorageService {

    private URLRepository repository;

    @Autowired
    public UrlStorageService(URLRepository repository) {
        this.repository = repository;
    }

    @Override
    public void put(String hash, String url) {
        try {
            repository.save(hash, url);
        } catch (URLRepositoryException e) {
            //TODO log instead print
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public String get(String hash) {
        try {
            return repository.get(hash);
        } catch (URLRepositoryException e) {
            //TODO log instead print
            System.out.println("Error occurred: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Map<String, String> getAll() {
        try {
            return repository.getAll();
        } catch (URLRepositoryException e) {
            //TODO log instead print
            System.out.println("Error occurred: " + e.getMessage());
        }

        return new HashMap<>();
    }

    @Override
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
