package com.vusachov.urlshortener.repositorues;

import com.vusachov.urlshortener.domain.Url;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, Long> {
}
