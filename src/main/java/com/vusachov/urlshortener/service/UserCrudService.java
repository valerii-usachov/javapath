package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.dto.NewUserDto;
import com.vusachov.urlshortener.entity.AccountType;
import com.vusachov.urlshortener.entity.User;
import com.vusachov.urlshortener.exception.EmailExistsException;
import com.vusachov.urlshortener.repositories.AccountTypeRepository;
import com.vusachov.urlshortener.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
final public class UserCrudService implements UserService, UserDetailsService {

    private UserRepository userRepository;

    private AccountTypeRepository accountTypeRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails save(final User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerNewUserAccount(NewUserDto userDto) throws EmailExistsException {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new EmailExistsException();
        }

        User user = new User();
        user.setAccountType(accountTypeRepository.findById(AccountType.DEFAULT_ID).orElse(null));
        user.setUsername(userDto.getUsername());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username " + username));
    }
}
