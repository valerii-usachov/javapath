package com.vusachov.urlshortener.dto;

import lombok.Data;

@Data
public class NewUserDto {

    private final String username;
    private final String password;
}
