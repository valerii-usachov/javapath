package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.URLShortener;
import com.vusachov.urlshortener.dto.OriginUrlGetResponseItemV1;
import com.vusachov.urlshortener.dto.OriginUrlPostRequestItemV1;
import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/hash")
public class ApiHashController {

    @Autowired
    private URLShortener urlConverter;

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/{hash}", produces = "application/json")
    public OriginUrlGetResponseItemV1 get(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        return new OriginUrlGetResponseItemV1(hash, originURL);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<OriginUrlGetResponseItemV1> listAll() {

        List<HashUrl> allUrls = storageService.getAll();

        List<OriginUrlGetResponseItemV1> list = allUrls.stream()
                .map(hashUrl -> new OriginUrlGetResponseItemV1(hashUrl.getHash(), hashUrl.getUrl()))
                .collect(Collectors.toList());

        return list;
    }

    @PostMapping(value = "", consumes = "application/json")
    public OriginUrlGetResponseItemV1 create(@Valid @RequestBody OriginUrlPostRequestItemV1 originUrlPost) {

        String originURL = originUrlPost.getOriginUrl();
        String hash = urlConverter.getHashFromOriginUrl(originURL);

        return new OriginUrlGetResponseItemV1(hash, originURL);
    }

    @DeleteMapping(value = "/{hash}")
    public OriginUrlGetResponseItemV1 delete(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        urlConverter.delete(hash);

        return new OriginUrlGetResponseItemV1(hash, originURL);
    }
}
