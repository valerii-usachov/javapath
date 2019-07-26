package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("urlStorageService")
public class UrlStorageService implements StorageService {

    private URLRepository repository;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UrlStorageService.class);

    @Autowired
    public UrlStorageService(URLRepository repository) {
        this.repository = repository;
    }

    @Override
    public void put(String hash, String url) {
        try {
            repository.save(hash, url);
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String get(String hash) {
        try {
            return repository.get(hash);
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public Map<String, String> getAll() {
        try {
            return repository.getAll();
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return new HashMap<>();
    }

    @Override
    public boolean delete(String hash) {
        try {
            return repository.delete(hash);
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    @Override
    public boolean isUnique(String originUrl) {
        try {
            return repository.getHashByOriginUrl(originUrl) == null;
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return true;
    }
}
