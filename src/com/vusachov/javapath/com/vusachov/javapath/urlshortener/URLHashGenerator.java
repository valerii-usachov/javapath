package com.vusachov.javapath.com.vusachov.javapath.urlshortener;

public interface URLHashGenerator {

    /**
     * @param url Origin URL
     * @return Hash
     */
    String getHash(String url);
}
