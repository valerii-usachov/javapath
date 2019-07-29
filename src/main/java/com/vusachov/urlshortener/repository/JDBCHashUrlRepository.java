package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCHashUrlRepository implements HashUrlRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JDBCHashUrlRepository.class);

    @Autowired
    public JDBCHashUrlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public HashUrl findOne(String hash) throws URLRepositoryException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM urls WHERE hash = ?", new Object[]{hash}, ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new URLRepositoryException(e);
        }
    }

    @Override
    public List<HashUrl> findAll() throws URLRepositoryException {
        try {
            return jdbcTemplate.query("SELECT * FROM urls", ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new URLRepositoryException(e);
        }
    }

    @Override
    public HashUrl save(HashUrl hashUrl) throws URLRepositoryException {
        try {
            jdbcTemplate.update(
                    "INSERT INTO urls (hash, url) VALUES (?, ?) ON CONFLICT (hash) DO NOTHING",
                    hashUrl.getHash(),
                    hashUrl.getUrl());
            return findOne(hashUrl.getHash());
        } catch (DataAccessException e) {
            throw new URLRepositoryException(e);
        }
    }

    @Override
    public boolean delete(String hash) throws URLRepositoryException {
        try {
            return jdbcTemplate.update("DELETE FROM urls WHERE hash = ?", hash) > 0;
        } catch (DataAccessException e) {
            throw new URLRepositoryException(e);
        }
    }

    @Override
    public HashUrl findOneByOriginUrl(String originUrl) throws URLRepositoryException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM urls WHERE url = ?", new Object[]{originUrl}, ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new URLRepositoryException(e);
        }
    }
}
