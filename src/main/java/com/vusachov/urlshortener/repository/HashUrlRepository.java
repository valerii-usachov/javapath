package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.repository.exception.HashUrlRepositoryException;
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

    HashUrl findOne(String hash) throws HashUrlRepositoryException;

    List<HashUrl> findAll() throws HashUrlRepositoryException;

    HashUrl save(HashUrl hashUrl) throws HashUrlRepositoryException;

    boolean delete(String hash) throws HashUrlRepositoryException;

    default HashUrl findOneByOriginUrl(String originUrl) throws HashUrlRepositoryException {
        return null;
    }
}
