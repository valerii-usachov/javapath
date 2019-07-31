package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.HashUrl;

import java.util.List;

public interface StorageService {

    void put(String hash, String url);

    String get(String hash);

    List<HashUrl> getAll();

    boolean delete(String hash);

    boolean isUnique(String originUrl);
}
