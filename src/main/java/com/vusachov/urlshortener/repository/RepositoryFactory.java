package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.db.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryFactory {

    @Autowired
    private ConnectionManager connectionManager;

    public URLRepository getRepository(URLRepositoryType type) {

        URLRepository repository;

        switch (type) {
            case FileSystem:
                repository = new FileSystemURLRepository();
                break;
            case DB:
                repository = new JDBCUrlRepository(connectionManager.getConnection());
                break;
            case InMemory:
            default:
                repository = new InMemoryURLRepository();
                break;
        }

        return repository;
    }
}
