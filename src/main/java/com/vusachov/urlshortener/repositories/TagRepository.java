package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.entity.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByName(String tagName);
}
