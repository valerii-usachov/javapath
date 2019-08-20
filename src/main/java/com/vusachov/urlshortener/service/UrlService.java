package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Url;

import java.util.List;

public interface UrlService {

    Url findOrCreateUrl(String url);

    List<Url> getAll();
}