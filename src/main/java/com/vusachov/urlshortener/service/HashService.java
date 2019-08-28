package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;

import java.util.List;

public interface HashService {

    Hash create(String url, String hashCode);

    Hash save(Hash hash);

    Hash get(String hashCode) throws ResourceNotFoundException;

    List<Hash> getAll();

    List<Hash> getAllForUser(User user);

    boolean delete(Hash hash);

    boolean isUnique(String hashCode);
}
