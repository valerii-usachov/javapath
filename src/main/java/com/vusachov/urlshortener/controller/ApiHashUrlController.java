package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.dto.HashUrlDeleteResponseItemV1;
import com.vusachov.urlshortener.dto.HashUrlGetResponseItemV1;
import com.vusachov.urlshortener.dto.HashUrlPostRequestItemV1;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.hashgenerator.URLHashGenerator;
import com.vusachov.urlshortener.service.HashUrlStorage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/hash")
public class ApiHashUrlController {

    private URLHashGenerator urlHashGenerator;

    private HashUrlStorage hashUrlStorage;

    public ApiHashUrlController(URLHashGenerator urlHashGenerator, HashUrlStorage hashUrlStorage) {
        this.urlHashGenerator = urlHashGenerator;
        this.hashUrlStorage = hashUrlStorage;
    }

    @GetMapping(value = "/{hash}", produces = "application/json")
    public HashUrlGetResponseItemV1 get(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashUrlStorage.get(hashCode);

        return new HashUrlGetResponseItemV1(hash);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<HashUrlGetResponseItemV1> listAll() {

        return hashUrlStorage.getAll()
                .stream()
                .map(HashUrlGetResponseItemV1::new)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "", consumes = "application/json")
    public HashUrlGetResponseItemV1 create(@Valid @RequestBody HashUrlPostRequestItemV1 urlHashPost) {

        String originURL = urlHashPost.getUrl();
        String hashCode = urlHashPost.getHash();

        if (hashCode == null) {
            hashCode = urlHashGenerator.getHash(originURL);
        }

        Hash hash = hashUrlStorage.create(originURL, hashCode);

        return new HashUrlGetResponseItemV1(hash);
    }

    @DeleteMapping(value = "/{hash}")
    public HashUrlDeleteResponseItemV1 delete(@PathVariable(value = "hash") String hashCode) {

        Hash hash = hashUrlStorage.get(hashCode);

        if (hash == null) {
            throw new ResourceNotFoundException();
        }

        boolean result = hashUrlStorage.delete(hash);

        return new HashUrlDeleteResponseItemV1(result);
    }
}
