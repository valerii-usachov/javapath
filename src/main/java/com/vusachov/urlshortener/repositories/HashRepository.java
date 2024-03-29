package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.entity.Hash;
import com.vusachov.urlshortener.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HashRepository extends CrudRepository<Hash, Long> {

    Optional<Hash> findByHash(String hash);

    Iterable<Hash> findAllByUser(User user);
}
