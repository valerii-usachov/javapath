package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.repositories.HashRepository;
import com.vusachov.urlshortener.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HashUrlCrudService implements HashUrlService {

    private final HashRepository hashRepository;
    private final UrlRepository urlRepository;

    @Override
    @Transactional
    public Hash create(String originURL, String hashCode) {
        Url url = this.findOrCreateUrl(originURL);

        Hash hash = new Hash(url, hashCode);

        this.save(hash);

        return hash;
    }

    @Override
    public Hash save(Hash hash) {
        return hashRepository.save(hash);
    }

    @Override
    public Hash get(String hashCode) {
        return hashRepository.findByHash(hashCode)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Hash> getAll() {
        List<Hash> hashes = new ArrayList<>();
        hashRepository.findAll().iterator().forEachRemaining(hashes::add);

        return hashes;
    }

    @Override
    public boolean delete(Hash hash) {
        hashRepository.delete(hash);

        return true;
    }

    @Override
    public boolean isUnique(String hashCode) {
        return !hashRepository.findByHash(hashCode).isPresent();
    }

    @Override
    public Url findOrCreateUrl(String url) {

        Url existingUrl = urlRepository.findByUrl(url);

        if (existingUrl != null) {
            return existingUrl;
        }

        Url newUrl = new Url(url);
        urlRepository.save(newUrl);

        return newUrl;
    }
}