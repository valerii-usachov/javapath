package com.vusachov.javapath.urlshortener.storage;

import com.vusachov.javapath.urlshortener.storage.repository.SqlUrlsRepository;

public class DbURLStorage implements URLStorage {

    private final SqlUrlsRepository repository;

    public DbURLStorage(SqlUrlsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void put(String hash, String url) {
        repository.saveUrl(hash, url);
    }

    @Override
    public String get(String hash) {

        return repository.getUrlByHash(hash);
    }
}
