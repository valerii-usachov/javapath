package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.repository.exception.URLRepositoryException;

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
     * Get Origin OriginUrlGet by hash
     *
     * @param hash Hash of origin URL
     * @return Origin URL
     * @throws URLRepositoryException Cannot read
     */
    String get(String hash) throws URLRepositoryException;

    /**
     * Get Origin OriginUrlGet by hash
     *
     * @param hash Hash of origin URL
     * @return Origin URL
     * @throws URLRepositoryException Cannot read
     */
    boolean delete(String hash) throws URLRepositoryException;
}
