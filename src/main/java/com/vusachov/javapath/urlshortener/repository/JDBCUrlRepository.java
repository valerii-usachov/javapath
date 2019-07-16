package com.vusachov.javapath.urlshortener.repository;

import com.vusachov.javapath.urlshortener.repository.exception.URLRepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUrlRepository implements URLRepository {

    private final Connection connection;

    private final String tableName;

    public JDBCUrlRepository(Connection connection) {
        this(connection, "urls");
    }

    public JDBCUrlRepository(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    @Override
    public String get(String hash) throws URLRepositoryException {
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
            throw new URLRepositoryException("Failed to execute query", e);
        }
    }

    @Override
    public void save(String hash, String url) throws URLRepositoryException {
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
            throw new URLRepositoryException("Failed to execute query", e);
        }
    }
}
