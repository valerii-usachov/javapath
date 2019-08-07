package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.domain.Url;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.repositories.HashRepository;
import com.vusachov.urlshortener.repositories.UrlRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HashUrlStorageService implements StorageService {

    private final HashRepository hashRepository;
    private final UrlRepository urlRepository;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(HashUrlStorageService.class);

    @Autowired
    public HashUrlStorageService(HashRepository hashRepository, UrlRepository urlRepository) {

        this.hashRepository = hashRepository;
        this.urlRepository = urlRepository;
    }

    @Override
    public Hash create(String originURL, String hashCode) {
        Url url = this.findOrCreateUrl(originURL);

        Hash hash = new Hash(url, hashCode);

        this.save(hash);

        return hash;
    }

    @Override
    public Hash save(Hash hash) {
        hashRepository.save(hash);

        return hash;
    }

    @Override
    public Hash get(String hashCode) {
        Optional<Hash> hashUrl = hashRepository.findByHash(hashCode);

        if (!hashUrl.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return hashUrl.get();
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