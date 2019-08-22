package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.entity.Tag;
import com.vusachov.urlshortener.dto.TagGetResponseItemV1;
import com.vusachov.urlshortener.service.TagService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tag")
public class ApiTagController {

    private TagService tagService;

    public ApiTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagGetResponseItemV1 get(@PathVariable(value = "id") Long id) {

        Tag tag = tagService.get(id);

        return new TagGetResponseItemV1(tag);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagGetResponseItemV1> listAll() {

        return tagService.getAll()
                .stream()
                .map(TagGetResponseItemV1::new)
                .collect(Collectors.toList());
    }
}
