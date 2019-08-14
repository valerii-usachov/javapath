package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.AccessToken;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.repositories.AccessTokenRepository;
import com.vusachov.urlshortener.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class AccessTokenService implements UserAuthenticationService {

    UserRepository userRepository;

    AccessTokenRepository accessTokenRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public Optional<String> createAccessToken(final String username, final String password) {

        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent() || !passwordEncoder.matches(password, user.get().getPassword())) {
            return Optional.empty();
        }

        clearExpiredTokens(user.get());

        final String uuid = UUID.randomUUID().toString();
        final LocalDateTime expiresOn = LocalDateTime.now().plusSeconds(5);

        final AccessToken accessToken = new AccessToken(uuid, user.get(), expiresOn);

        accessTokenRepository.save(accessToken);

        return Optional.of(accessToken.getToken());
    }

    @Override
    public Optional<User> findUserByAccessToken(final String token) {

        AccessToken accessToken = accessTokenRepository.findByToken(token).orElse(null);

        if (accessToken == null) {
            return Optional.empty();
        }

        if (accessToken.isExpired()) {
            accessTokenRepository.delete(accessToken);

            return Optional.empty();
        }

        return Optional.of(accessToken.getUser());
    }

    @Override
    public void logout(final User user) {

        //TODO delete only one access token
        Iterable<AccessToken> userAccessTokens = accessTokenRepository.findAllByUser(user);
        accessTokenRepository.deleteAll(userAccessTokens);
    }

    private void clearExpiredTokens(User user) {
        Iterable<AccessToken> userAccessTokens = accessTokenRepository.findAllByUser(user);

        userAccessTokens.iterator().forEachRemaining((accessToken) -> {
            if (accessToken.isExpired()) accessTokenRepository.delete(accessToken);
        });
    }
}