package com.vusachov.urlshortener.repositorues;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
