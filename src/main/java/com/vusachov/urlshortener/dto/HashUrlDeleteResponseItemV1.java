package com.vusachov.urlshortener.dto;

public class HashUrlDeleteResponseItemV1 {

    private final boolean success;

    public HashUrlDeleteResponseItemV1(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
