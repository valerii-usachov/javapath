package com.vusachov.urlshortener.repositorues;

import com.vusachov.urlshortener.domain.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
