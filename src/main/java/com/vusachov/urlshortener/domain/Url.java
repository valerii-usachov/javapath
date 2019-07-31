package com.vusachov.urlshortener.domain;

import javax.persistence.*;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String hash;

    @Column(length = 2083)
    private String url;

    @ManyToOne
    private User user;

    public Url(String hash, String url) {
        this.hash = hash;
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
