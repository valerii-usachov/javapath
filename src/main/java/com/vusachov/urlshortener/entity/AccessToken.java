package com.vusachov.urlshortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
public class AccessToken {

    public AccessToken() {

    }

    @Id
    private String token;

    @ManyToOne
    private User user;

    private LocalDateTime expiresOn;

    public boolean isExpired() {
        return expiresOn.isBefore(LocalDateTime.now());
    }
}
