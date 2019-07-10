package com.vusachov.javapath.urlshortener.storage;

public interface URLStorage {

    void put(String hash, String url);

    String get(String hash);
}
