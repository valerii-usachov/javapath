package com.vusachov.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vusachov.urlshortener.validator.constraints.StorageUniqueURL;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OriginUrlPostRequestItemV1 {

    @NotNull(message = "This value cannot be empty")
    @URL(message = "Value must be a valid URL")
    @StorageUniqueURL
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
