package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.service.UserAuthenticationService;
import com.vusachov.urlshortener.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
final class PublicUsersController {

    @NonNull
    UserAuthenticationService authentication;

    @NonNull
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    String register(@RequestBody User user) {
        userService.save(user);

        return login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    String login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {

        return authentication
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid login and/or password"));
    }
}