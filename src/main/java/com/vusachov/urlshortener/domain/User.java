package com.vusachov.urlshortener.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @OneToOne(fetch = FetchType.EAGER)
    private AccountType accountType;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Lob
    private Byte[] avatar;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Url> urls;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(Byte[] avatar) {
        this.avatar = avatar;
    }
}
