package com.vusachov.javapath.urlshortener.storage.repository;

import java.sql.*;

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
            String sqlQuery = String.format("SELECT url FROM %s WHERE hash = ?", tableName);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, hash);

            ResultSet resultSet = statement.executeQuery();

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
            String sqlQuery = String.format(
                    "INSERT INTO %s (hash, url) VALUES (?, ?) ON CONFLICT (hash) DO NOTHING",
                    tableName
            );

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, hash);
            statement.setString(2, url);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to  execute query: " + e.getMessage());
        }
    }
}
