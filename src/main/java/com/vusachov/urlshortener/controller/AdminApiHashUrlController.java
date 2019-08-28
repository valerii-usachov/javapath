package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.dto.HashUrlGetResponseItemV1;
import com.vusachov.urlshortener.service.HashService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin_api/v1/hash")
public class AdminApiHashUrlController {

    private HashService hashService;

    public AdminApiHashUrlController(HashService hashService) {
        this.hashService = hashService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HashUrlGetResponseItemV1> listAll() {

        return hashService.getAll()
                .stream()
                .map(HashUrlGetResponseItemV1::new)
                .collect(Collectors.toList());
    }
}
