package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.service.HashService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HashUrlController {

    private HashService hashService;

    public HashUrlController(HashService hashService) {
        this.hashService = hashService;
    }

    @GetMapping("/{hash}")
    public RedirectView redirectByHash(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashService.get(hashCode);

        if (hash == null) {
            throw new ResourceNotFoundException();
        }

        return new RedirectView(hash.getUrl().getUrl());
    }
}
