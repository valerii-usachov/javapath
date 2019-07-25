package com.vusachov.urlshortener.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionManager {

    private String dbUrl;

    private String dbUser;

    private String dbPass;

    public ConnectionManager(
            @Value("${db.url}") String dbUrl,
            @Value("${db.user}") String dbUser,
            @Value("${db.pass}") String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    @Bean
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to db: " + e.getMessage(), e);
        }
    }
}
