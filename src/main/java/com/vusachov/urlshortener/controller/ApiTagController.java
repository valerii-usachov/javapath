package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.domain.Tag;
import com.vusachov.urlshortener.dto.TagGetResponseItemV1;
import com.vusachov.urlshortener.service.TagStorage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tag")
public class ApiTagController {

    private TagStorage tagStorage;

    public ApiTagController(TagStorage tagStorage) {
        this.tagStorage = tagStorage;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public TagGetResponseItemV1 get(@PathVariable(value = "id") Long id) {

        Tag tag = tagStorage.get(id);

        return new TagGetResponseItemV1(tag);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<TagGetResponseItemV1> listAll() {

        return tagStorage.getAll()
                .stream()
                .map(TagGetResponseItemV1::new)
                .collect(Collectors.toList());
    }
}
