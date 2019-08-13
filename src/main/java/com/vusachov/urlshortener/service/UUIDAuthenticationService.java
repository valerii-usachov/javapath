package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.AccessToken;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.repositories.AccessTokenRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class UUIDAuthenticationService implements UserAuthenticationService {

    UserService userService;

    AccessTokenRepository accessTokenRepository;

    @Override
    public Optional<String> login(final String username, final String password) {

        Optional<User> user = userService.findByUsername(username);

        if (!user.isPresent() || !password.equals(user.get().getPassword())) {
            return Optional.empty();
        }

        final String uuid = UUID.randomUUID().toString();
        final LocalDateTime expiresOn = LocalDateTime.now().plusMinutes(60);

        final AccessToken accessToken = new AccessToken(uuid, user.get(), expiresOn);

        accessTokenRepository.save(accessToken);

        return Optional.of(accessToken.getToken());
    }

    @Override
    public Optional<User> findByToken(final String token) {
        return userService.findByToken(token);
    }

    @Override
    public void logout(final User user) {

        //TODO delete only one access token
        Iterable<AccessToken> userAccessTokens = accessTokenRepository.findAllByUser(user);
        accessTokenRepository.deleteAll(userAccessTokens);
    }
}