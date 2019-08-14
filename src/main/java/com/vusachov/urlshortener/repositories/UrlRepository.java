package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.domain.Url;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Nullable;

public interface UrlRepository extends CrudRepository<Url, Long> {

    @Nullable
    Url findByUrl(String url);
}
