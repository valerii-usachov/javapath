package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.service.HashUrlStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HashUrlController {

    private HashUrlStorage hashUrlStorage;

    public HashUrlController(HashUrlStorage hashUrlStorage) {
        this.hashUrlStorage = hashUrlStorage;
    }

    @GetMapping("/{hash}")
    public RedirectView redirectByHash(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashUrlStorage.get(hashCode);

        if (hash == null) {
            throw new ResourceNotFoundException();
        }

        return new RedirectView(hash.getUrl().getUrl());
    }
}
