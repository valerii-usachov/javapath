package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface HashUrlRepository {

    RowMapper<HashUrl> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new HashUrl(
                resultSet.getString("hash"),
                resultSet.getString("url")
        );
    };

    HashUrl findOne(String hash) throws URLRepositoryException;

    List<HashUrl> findAll() throws URLRepositoryException;

    HashUrl save(HashUrl hashUrl) throws URLRepositoryException;

    boolean delete(String hash) throws URLRepositoryException;

    default HashUrl findOneByOriginUrl(String originUrl) throws URLRepositoryException {
        return null;
    }
}
