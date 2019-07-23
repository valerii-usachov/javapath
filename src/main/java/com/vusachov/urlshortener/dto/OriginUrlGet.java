package com.vusachov.urlshortener.dto;

public class OriginUrlGet {

    private final String hash;
    private final String originUrl;

    public OriginUrlGet(String hash, String originUrl) {
        this.hash = hash;
        this.originUrl = originUrl;
    }

    public String getHash() {
        return hash;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
