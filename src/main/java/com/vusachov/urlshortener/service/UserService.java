package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.dto.NewUserDto;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.exception.EmailExistsException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    UserDetails save(User user);

    Optional<User> findByUsername(String username);

    User registerNewUserAccount(NewUserDto userDto) throws EmailExistsException;
}
