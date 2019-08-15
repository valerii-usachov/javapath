package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.dto.HashUrlGetResponseItemV1;
import com.vusachov.urlshortener.service.HashUrlService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin_api/v1/hash")
public class AdminApiHashUrlController {

    private HashUrlService hashUrlService;

    public AdminApiHashUrlController(HashUrlService hashUrlService) {
        this.hashUrlService = hashUrlService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HashUrlGetResponseItemV1> listAll() {

        return hashUrlService.getAll()
                .stream()
                .map(HashUrlGetResponseItemV1::new)
                .collect(Collectors.toList());
    }
}
