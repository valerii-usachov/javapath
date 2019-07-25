package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.repository.exception.URLRepositoryException;

import java.util.Map;

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
     * Get Origin OriginUrlGetResponseItemV1 by hash
     *
     * @param hash Hash of origin URL
     * @return Origin URL
     * @throws URLRepositoryException Cannot read
     */
    String get(String hash) throws URLRepositoryException;

    /**
     * Get All
     *
     * @return Origin URL
     * @throws URLRepositoryException Cannot read
     */
    Map<String, String> getAll() throws URLRepositoryException;

    /**
     * Get Origin OriginUrlGetResponseItemV1 by hash
     *
     * @param hash Hash of origin URL
     * @return Origin URL
     * @throws URLRepositoryException Cannot read
     */
    boolean delete(String hash) throws URLRepositoryException;
}
