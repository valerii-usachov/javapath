package com.vusachov.javapath.urlshortener.storage.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUrlsRepository {

    private final Connection connection;

    private final String tableName;

    public SqlUrlsRepository(Connection connection) {
        this(connection, "urls");
    }

    public SqlUrlsRepository(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    public String getUrlByHash(String hash) {
        try {
            Statement statement = connection.createStatement();

            String sqlQuery = String.format("SELECT url FROM %s WHERE hash = '%s'", tableName, hash);
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getString("url");

        } catch (SQLException e) {
            System.out.println("Failed to execute query: " + e.getMessage());
        }

        return null;
    }

    public void saveUrl(String hash, String url) {
        try {
            Statement statement = connection.createStatement();

            String sqlQuery = String.format(
                    "INSERT INTO %s (hash, url) VALUES ('%s', '%s') ON CONFLICT (hash) DO NOTHING",
                    tableName,
                    hash,
                    url
            );
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            System.out.println("Failed to  execute query: " + e.getMessage());
        }
    }
}
