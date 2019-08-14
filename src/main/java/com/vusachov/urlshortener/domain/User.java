package com.vusachov.urlshortener.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @ManyToOne
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
    private Set<Hash> hashes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
