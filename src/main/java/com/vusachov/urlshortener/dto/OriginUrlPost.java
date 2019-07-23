package com.vusachov.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginUrlPost {

    private String originUrl;

    public OriginUrlPost() {
    }

    public OriginUrlPost(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
