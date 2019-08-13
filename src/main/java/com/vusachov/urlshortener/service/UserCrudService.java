package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.AccessToken;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.repositories.AccessTokenRepository;
import com.vusachov.urlshortener.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
final class UserCrudService implements UserService {

    private UserRepository userRepository;

    private AccessTokenRepository accessTokenRepository;

    @Override
    public UserDetails save(final User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByToken(final String token) {
        return accessTokenRepository.findByToken(token).map(AccessToken::getUser);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }
}
