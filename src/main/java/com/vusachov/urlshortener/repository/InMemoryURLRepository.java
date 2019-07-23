package com.vusachov.urlshortener.repository;

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

    @Override
    public boolean delete(String hash) {

        String originUrl = get(hash);

        if (originUrl == null) {
            return false;
        }

        shortenURLs.remove(originUrl);

        return true;
    }
}
