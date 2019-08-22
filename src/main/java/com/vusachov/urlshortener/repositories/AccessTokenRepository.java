package com.vusachov.urlshortener.repositories;

import com.vusachov.urlshortener.entity.AccessToken;
import com.vusachov.urlshortener.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {

    Optional<AccessToken> findByToken(String token);

    Iterable<AccessToken> findAllByUser(User user);
}
