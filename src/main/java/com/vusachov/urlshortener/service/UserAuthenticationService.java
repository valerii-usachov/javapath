package com.vusachov.urlshortener.service;


import com.vusachov.urlshortener.entity.User;

import java.util.Optional;

public interface UserAuthenticationService {
    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param username
     * @param password
     * @return an {@link Optional} of a user when login succeeds
     */
    Optional<String> createAccessToken(String username, String password);

    /**
     * Finds a user by its dao-key.
     *
     * @param token user dao key
     * @return
     */
    Optional<User> findUserByAccessToken(String token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(User user);
}
