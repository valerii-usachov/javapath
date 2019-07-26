package com.vusachov.urlshortener.service;

import java.util.Map;

public interface StorageService {

    void put(String hash, String url);

    String get(String hash);

    Map<String, String> getAll();

    boolean delete(String hash);

    boolean isUnique(String originUrl);
}
