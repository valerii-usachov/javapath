package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    UserDetails save(User user);

    Optional<User> findByToken(String token);

    Optional<User> findByUsername(String username);
}
