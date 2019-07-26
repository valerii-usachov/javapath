package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JDBCUrlRepository implements URLRepository {

    private final Connection connection;

    @Autowired
    JDBCUrlRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String get(String hash) throws URLRepositoryException {
        try {
            String sqlQuery = "SELECT url FROM urls WHERE hash = ?";
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
    public Map<String, String> getAll() throws URLRepositoryException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT hash, url FROM urls");

            HashMap<String, String> result = new HashMap<>();

            while (resultSet.next()) {
                result.put(resultSet.getString("hash"), resultSet.getString("url"));
            }

            return result;

        } catch (SQLException e) {
            throw new URLRepositoryException("Failed to execute query", e);
        }
    }

    @Override
    public void save(String hash, String url) throws URLRepositoryException {
        try {
            String sqlQuery = "INSERT INTO urls (hash, url) VALUES (?, ?) ON CONFLICT (hash) DO NOTHING";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, hash);
            statement.setString(2, url);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new URLRepositoryException("Failed to execute query", e);
        }
    }

    @Override
    public boolean delete(String hash) throws URLRepositoryException {
        try {
            String sqlQuery = "DELETE FROM urls WHERE hash = ?";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, hash);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new URLRepositoryException("Failed to execute query", e);
        }
    }

    @Override
    public String getHashByOriginUrl(String originUrl) throws URLRepositoryException {
        try {
            String sqlQuery = "SELECT hash FROM urls WHERE url = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, originUrl);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return resultSet.getString("hash");

        } catch (SQLException e) {
            throw new URLRepositoryException("Failed to execute query", e);
        }
    }
}
