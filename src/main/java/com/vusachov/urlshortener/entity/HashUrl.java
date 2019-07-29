package com.vusachov.urlshortener.entity;

public class HashUrl {

    private String hash;

    private String url;

    public HashUrl(String hash, String url) {
        this.hash = hash;
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public String getUrl() {
        return url;
    }
}
