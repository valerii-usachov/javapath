package com.vusachov.javapath.urlshortener.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private final String dbHost;
    private final String dbName;
    private final String dbUser;
    private final String dbPass;

    private Connection connection;

    public ConnectionManager(Properties props) {
        dbHost = props.getProperty("db_host");
        dbName = props.getProperty("db_name");
        dbUser = props.getProperty("db_user");
        dbPass = props.getProperty("db_pass");
    }

    public Connection getConnection() {
        if (connection == null) {
            initConnection();
        }

        return connection;
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(dbHost + "/" + dbName, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to db: " + e.getMessage(), e);
        }
    }
}
