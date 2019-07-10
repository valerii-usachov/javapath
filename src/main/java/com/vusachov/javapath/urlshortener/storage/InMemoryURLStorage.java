package com.vusachov.javapath.urlshortener.storage;

import java.util.HashMap;

public class InMemoryURLStorage implements URLStorage {

    private HashMap<String, String> shortenURLs = new HashMap<>();

    @Override
    public void put(String hash, String url) {
        shortenURLs.put(hash, url);
    }

    @Override
    public String get(String hash) {
        return shortenURLs.get(hash);
    }
}
