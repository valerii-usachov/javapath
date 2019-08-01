package com.vusachov.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountType {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private int urlExpPeriod = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrlExpPeriod() {
        return urlExpPeriod;
    }

    public void setUrlExpPeriod(int urlExpPeriod) {
        this.urlExpPeriod = urlExpPeriod;
    }
}
