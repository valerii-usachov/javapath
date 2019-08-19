package com.vusachov.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vusachov.urlshortener.validator.constraints.StorageUniqueHash;
import com.vusachov.urlshortener.validator.constraints.URLResponseCode;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HashUrlPostRequestItemV1 {

    @NotNull(message = "This value cannot be empty")
    @URL(message = "Value must be a valid URL")
    @URLResponseCode
    private String url;

    @StorageUniqueHash
    private String hash;

    public HashUrlPostRequestItemV1() {
    }

    public HashUrlPostRequestItemV1(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getHash() {
        return hash;
    }
}
