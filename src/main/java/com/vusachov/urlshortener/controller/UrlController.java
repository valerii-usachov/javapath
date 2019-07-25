package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.URLConverter;
import com.vusachov.urlshortener.controller.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UrlController {

    @Autowired
    private URLConverter urlConverter;

    @GetMapping("/{hash}")
    public RedirectView redirectByHash(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        if (originURL == null) {
            throw new ResourceNotFoundException();
        }

        return new RedirectView(originURL);
    }
}
