package com.vusachov.urlshortener.hashgenerator;

import java.time.LocalDateTime;

public interface URLHashGenerator {

    /**
     * @param url Origin URL
     * @return Hash
     */
    String getHash(String url);

    default String getRandom() {
        return LocalDateTime.now().toString();
    }
}
