package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.event.UrlCreatedEvent;
import com.vusachov.urlshortener.repositories.UrlRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UrlCrudService implements UrlService {

    private final UrlRepository urlRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Url findOrCreateUrl(String url) {

        Url existingUrl = urlRepository.findByUrl(url);

        if (existingUrl != null) {
            return existingUrl;
        }

        Url newUrl = new Url(url);
        urlRepository.save(newUrl);

        applicationEventPublisher.publishEvent(
                new UrlCreatedEvent(this, newUrl.getId())
        );

        return newUrl;
    }

    @Override
    public List<Url> getAll() {
        List<Url> urls = new ArrayList<>();
        urlRepository.findAll().iterator().forEachRemaining(urls::add);

        return urls;
    }
}