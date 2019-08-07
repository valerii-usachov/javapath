package com.vusachov.urlshortener.dto;

import com.vusachov.urlshortener.domain.Tag;

public class TagGetResponseItemV1 {

    private final Long id;
    private final String name;

    public TagGetResponseItemV1(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
