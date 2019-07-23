package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.Config;
import com.vusachov.urlshortener.URLConverter;
import com.vusachov.urlshortener.controller.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.repository.RepositoryFactory;
import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.URLRepositoryType;
import com.vusachov.urlshortener.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UrlController {

    private URLConverter urlConverter;

    {
        String baseUrl = Config.getProperty("baseURL");
        URLRepository repository = RepositoryFactory.getRepository(URLRepositoryType.DB);
        StorageService storageService = new StorageService(repository);

        urlConverter = new URLConverter(baseUrl, storageService);
    }

    @GetMapping("/{hash}")
    public RedirectView redirectByHash(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        if (originURL == null) {
            throw new ResourceNotFoundException();
        }

        return new RedirectView(originURL);
    }
}
