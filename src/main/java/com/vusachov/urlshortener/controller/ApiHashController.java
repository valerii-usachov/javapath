package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.URLShortener;
import com.vusachov.urlshortener.controller.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.dto.OriginUrlGetResponseItemV1;
import com.vusachov.urlshortener.dto.OriginUrlPostRequestItemV1;
import com.vusachov.urlshortener.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        if (originURL == null) {
            throw new ResourceNotFoundException();
        }

        return new OriginUrlGetResponseItemV1(hash, originURL);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<OriginUrlGetResponseItemV1> listAll() {

        Map<String, String> allUrl = storageService.getAll();
        List<OriginUrlGetResponseItemV1> list = new ArrayList<>();

        allUrl.forEach((hash, originURL) -> list.add(new OriginUrlGetResponseItemV1(hash, originURL)));

        return list;
    }

    @PostMapping(value = "/", consumes = "application/json")
    public OriginUrlGetResponseItemV1 create(@Valid @RequestBody OriginUrlPostRequestItemV1 originUrlPost) {

        String originURL = originUrlPost.getOriginUrl();
        String hash = urlConverter.getHashFromOriginUrl(originURL);

        return new OriginUrlGetResponseItemV1(hash, originURL);
    }

    @DeleteMapping(value = "/{hash}")
    public OriginUrlGetResponseItemV1 delete(@PathVariable(value = "hash") String hash) {

        String originURL = urlConverter.getOriginUrlByHash(hash);

        if (originURL == null) {
            throw new ResourceNotFoundException();
        }

        urlConverter.delete(hash);

        return new OriginUrlGetResponseItemV1(hash, originURL);
    }
}
