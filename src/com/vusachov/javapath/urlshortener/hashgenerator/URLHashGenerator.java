package com.vusachov.javapath.urlshortener.hashgenerator;

public interface URLHashGenerator {

    /**
     * @param url Origin URL
     * @return Hash
     */
    String getHash(String url);
}
