package com.vusachov.urlshortener.db;

import com.vusachov.urlshortener.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    private Connection connection;

    public ConnectionManager(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public ConnectionManager(Config config) {
        this.dbUrl = config.getProperty("db_url");
        this.dbUser = config.getProperty("db_user");
        this.dbPass = config.getProperty("db_pass");
    }

    public Connection getConnection() {
        if (connection == null) {
            initConnection();
        }

        return connection;
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to db: " + e.getMessage(), e);
        }
    }
}
