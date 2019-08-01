package com.vusachov.urlshortener.repositorues;

import com.vusachov.urlshortener.domain.Hash;
import org.springframework.data.repository.CrudRepository;

public interface HashRepository extends CrudRepository<Hash, Long> {
}
