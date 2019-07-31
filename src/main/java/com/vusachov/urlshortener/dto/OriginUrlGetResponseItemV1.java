package com.vusachov.urlshortener.dto;

public class OriginUrlGetResponseItemV1 {

    private final String hash;
    private final String originUrl;

    public OriginUrlGetResponseItemV1(String hash, String originUrl) {
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
