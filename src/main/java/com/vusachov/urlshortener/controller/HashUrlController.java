package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.service.HashUrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HashUrlController {

    private HashUrlService hashUrlService;

    public HashUrlController(HashUrlService hashUrlService) {
        this.hashUrlService = hashUrlService;
    }

    @GetMapping("/{hash}")
    public RedirectView redirectByHash(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashUrlService.get(hashCode);

        if (hash == null) {
            throw new ResourceNotFoundException();
        }

        return new RedirectView(hash.getUrl().getUrl());
    }
}
