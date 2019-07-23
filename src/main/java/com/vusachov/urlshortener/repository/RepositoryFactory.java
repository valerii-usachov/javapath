package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.Config;
import com.vusachov.urlshortener.db.ConnectionManager;

public class RepositoryFactory {

    public static URLRepository getRepository(URLRepositoryType type) {

        URLRepository repository;

        switch (type) {
            case FileSystem:
                repository = new FileSystemURLRepository();
                break;
            case DB:
                ConnectionManager connectionManager = new ConnectionManager(
                        Config.getProperty("db_url"),
                        Config.getProperty("db_user"),
                        Config.getProperty("db_pass")
                );

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
