package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.Config;
import com.vusachov.urlshortener.URLConverter;
import com.vusachov.urlshortener.controller.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.dto.OriginUrlGet;
import com.vusachov.urlshortener.dto.OriginUrlPost;
import com.vusachov.urlshortener.repository.RepositoryFactory;
import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.URLRepositoryType;
import com.vusachov.urlshortener.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/hash")
public class ApiHashController {

    private URLConverter urlConverter;
    private StorageService storageService;

    {
        String baseUrl = Config.getProperty("baseURL");
        URLRepository repository = RepositoryFactory.getRepository(URLRepositoryType.DB);
        storageService = new StorageService(repository);
        urlConverter = new URLConverter(baseUrl, storageService);
    }

    @GetMapping(value = "/{hash}", produces = "application/json")
    public OriginUrlGet get(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        if (originURL == null) {
            throw new ResourceNotFoundException();
        }

        return new OriginUrlGet(hash, originURL);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<OriginUrlGet> listOf2() {

        String hash1 = "11c15918e86e2bc43a98d4ac988afdb2";
        String hash2 = "468cbe815d5fb6d2d78118d83ccc6d2d";

        String originURL1 = urlConverter.getOriginUrlByHash(hash1);
        String originURL2 = urlConverter.getOriginUrlByHash(hash2);

        List<OriginUrlGet> list = new ArrayList<>();
        list.add(new OriginUrlGet(hash1, originURL1));
        list.add(new OriginUrlGet(hash2, originURL2));

        return list;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public OriginUrlGet create(@RequestBody OriginUrlPost originUrlPost) {

        String originURL = originUrlPost.getOriginUrl();
        String hash = urlConverter.getHashFromOriginUrl(originURL);

        return new OriginUrlGet(hash, originURL);
    }

    @DeleteMapping(value = "/{hash}")
    public OriginUrlGet delete(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        if (originURL == null) {
            throw new ResourceNotFoundException();
        }

        storageService.delete(hash);

        return new OriginUrlGet(hash, originURL);
    }
}
