package com.vusachov.urlshortener.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AccountType {

    public static final String DEFAULT_ID = "free";

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private int urlExpPeriod = 0;
}
