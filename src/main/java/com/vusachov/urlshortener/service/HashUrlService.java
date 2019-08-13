package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;

import java.util.List;

public interface HashUrlService {

    Hash create(String url, String hashCode);

    Hash save(Hash hash);

    Hash get(String hashCode) throws ResourceNotFoundException;

    List<Hash> getAll();

    boolean delete(Hash hash);

    boolean isUnique(String hashCode);

    Url findOrCreateUrl(String url);
}
