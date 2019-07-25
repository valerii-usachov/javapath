package com.vusachov.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginUrlPostRequestItemV1 {

    @NotNull
    private String originUrl;

    public OriginUrlPostRequestItemV1() {
    }

    public OriginUrlPostRequestItemV1(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }
}
