package com.vusachov.urlshortener.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RepositoryFactory {

    public HashUrlRepository getRepository(URLRepositoryType type) {

        HashUrlRepository repository;

        switch (type) {
            case FileSystem:
                repository = new FileSystemHashUrlRepository();
                break;
            case DB:
                repository = new JDBCHashUrlRepository(new JdbcTemplate());
                break;
            case InMemory:
            default:
                repository = new InMemoryHashUrlRepository();
                break;
        }

        return repository;
    }
}
