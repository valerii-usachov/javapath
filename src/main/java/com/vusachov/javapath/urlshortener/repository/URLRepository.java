package com.vusachov.javapath.urlshortener.repository;

import com.vusachov.javapath.urlshortener.repository.exception.URLRepositoryException;

public interface URLRepository {

    /**
     * Store hash-url pair
     *
     * @param hash Hash
     * @param url  Origin URL
     * @throws URLRepositoryException Cannot save
     */
    void save(String hash, String url) throws URLRepositoryException;

    /**
     * Get Origin Url by hash
     *
     * @param hash Hash of origin URL
     * @return Origin URL
     * @throws URLRepositoryException Cannot read
     */
    String get(String hash) throws URLRepositoryException;
}
