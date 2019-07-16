package com.vusachov.javapath.urlshortener.repository;

import com.vusachov.javapath.urlshortener.Config;
import com.vusachov.javapath.urlshortener.db.ConnectionManager;

public class RepositoryFactory {

    public static URLRepository getRepository(URLRepositoryType type) {

        URLRepository repository;

        switch (type) {
            case FileSystem:
                repository = new FileSystemURLRepository();
                break;
            case DB:
                ConnectionManager connectionManager = new ConnectionManager(Config.getConfigProperties());
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
