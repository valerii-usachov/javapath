package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.domain.Hash;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HashRepository extends CrudRepository<Hash, Long> {

    Optional<Hash> findByHash(String hash);
}
