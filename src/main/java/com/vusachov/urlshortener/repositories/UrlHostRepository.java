package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.entity.UrlHost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UrlHostRepository extends CrudRepository<UrlHost, Long> {

    List<UrlHost> findAllByUrl(Url url);
}
