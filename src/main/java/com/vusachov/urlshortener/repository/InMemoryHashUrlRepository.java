package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.entity.HashUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHashUrlRepository implements HashUrlRepository {

    private Map<String, String> shortenURLs = new HashMap<>();

    @Override
    public HashUrl save(HashUrl hashUrl) {
        shortenURLs.put(hashUrl.getHash(), hashUrl.getUrl());

        return hashUrl;
    }

    @Override
    public HashUrl findOne(String hash) {
        String originUrl = shortenURLs.get(hash);

        if (originUrl != null) {
            return new HashUrl(hash, originUrl);
        }

        return null;
    }

    @Override
    public List<HashUrl> findAll() {
        ArrayList<HashUrl> list = new ArrayList<>();

        shortenURLs.forEach((hash, originUrl) -> list.add(new HashUrl(hash, originUrl)));

        return list;
    }

    @Override
    public boolean delete(String hash) {
        HashUrl hashUrl = findOne(hash);

        if (hashUrl == null) {
            return false;
        }

        shortenURLs.remove(hashUrl.getUrl());

        return true;
    }
}
