package com.vusachov.javapath.urlshortener.repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryURLRepository implements URLRepository {

    private Map<String, String> shortenURLs = new HashMap<>();

    @Override
    public void save(String hash, String url) {
        shortenURLs.put(hash, url);
    }

    @Override
    public String get(String hash) {
        return shortenURLs.get(hash);
    }
}
