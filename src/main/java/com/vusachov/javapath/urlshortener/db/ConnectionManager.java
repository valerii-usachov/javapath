package com.vusachov.javapath.urlshortener.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private Connection connection;

    public ConnectionManager(Properties props) {

        final String dbHost = props.getProperty("db_host");
        final String dbName = props.getProperty("db_name");
        final String dbUser = props.getProperty("db_user");
        final String dbPass = props.getProperty("db_pass");

        try {
            connection = DriverManager.getConnection(dbHost + "/" + dbName, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to db: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {

        return connection;
    }
}
