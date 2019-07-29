package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.repository.HashUrlRepository;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("urlStorageService")
public class UrlStorageService implements StorageService {

    private HashUrlRepository repository;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UrlStorageService.class);

    @Autowired
    public UrlStorageService(HashUrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public void put(String hash, String url) {
        try {
            repository.save(new HashUrl(hash, url));
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String get(String hash) {
        try {
            HashUrl hashUrl = repository.findOne(hash);
            return hashUrl != null ? hashUrl.getUrl() : null;
        } catch (URLRepositoryException e) {
            log.info(e.getMessage());
        }

        return null;
    }

    @Override
    public List<HashUrl> getAll() {
        try {
            return repository.findAll();
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return new ArrayList<>();
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
            return repository.findOneByOriginUrl(originUrl) == null;
        } catch (URLRepositoryException e) {
            log.error(e.getMessage(), e);
        }

        return true;
    }
}