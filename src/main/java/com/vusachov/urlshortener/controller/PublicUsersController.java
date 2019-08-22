package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.dto.NewUserDto;
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
    String register(@RequestBody NewUserDto userDto) {
        User user = userService.registerNewUserAccount(userDto);

        return login(user.getUsername(), userDto.getPassword());
    }

    @PostMapping("/login")
    String login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {

        return authentication
                .createAccessToken(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid login and/or password"));
    }
}