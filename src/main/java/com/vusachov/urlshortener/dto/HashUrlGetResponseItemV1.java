package com.vusachov.urlshortener.dto;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.domain.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class HashUrlGetResponseItemV1 {

    private final Long id;
    private final String hash;
    private final String url;
    private final Set<String> tags;

    public HashUrlGetResponseItemV1(Hash hash) {
        this.id = hash.getId();
        this.hash = hash.getHash();
        this.url = hash.getUrl().getUrl();
        this.tags = hash.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public String getUrl() {
        return url;
    }

    public Set<String> getTags() {
        return tags;
    }
}
