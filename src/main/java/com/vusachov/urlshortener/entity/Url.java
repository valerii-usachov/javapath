package com.vusachov.urlshortener.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2083, unique = true, nullable = false)
    private String url;

    public Url() {
    }

    public Url(String url) {
        this.url = url;
    }
}
