package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.dto.HashUrlDeleteResponseItemV1;
import com.vusachov.urlshortener.dto.HashUrlGetResponseItemV1;
import com.vusachov.urlshortener.dto.HashUrlPostRequestItemV1;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.hashgenerator.URLHashGenerator;
import com.vusachov.urlshortener.service.HashUrlService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/hash")
public class ApiHashUrlController {

    private URLHashGenerator urlHashGenerator;

    private HashUrlService hashUrlService;

    public ApiHashUrlController(URLHashGenerator urlHashGenerator, HashUrlService hashUrlService) {
        this.urlHashGenerator = urlHashGenerator;
        this.hashUrlService = hashUrlService;
    }

    @GetMapping(value = "/{hash}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashUrlGetResponseItemV1 get(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashUrlService.get(hashCode);

        return new HashUrlGetResponseItemV1(hash);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HashUrlGetResponseItemV1> listAll(@AuthenticationPrincipal final User user) {

        return hashUrlService.getAllForUser(user)
                .stream()
                .map(HashUrlGetResponseItemV1::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HashUrlGetResponseItemV1 create(@Valid @RequestBody HashUrlPostRequestItemV1 urlHashPost) {

        String originURL = urlHashPost.getUrl();
        String hashCode = urlHashPost.getHash();

        if (hashCode == null) {
            hashCode = urlHashGenerator.getHash(originURL);
        }

        Hash hash = hashUrlService.create(originURL, hashCode);

        return new HashUrlGetResponseItemV1(hash);
    }

    @DeleteMapping(value = "/{hash}")
    public HashUrlDeleteResponseItemV1 delete(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashUrlService.get(hashCode);

        if (hash == null) {
            throw new ResourceNotFoundException();
        }

        boolean result = hashUrlService.delete(hash);

        return new HashUrlDeleteResponseItemV1(result);
    }
}
