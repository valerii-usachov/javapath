package com.vusachov.javapath.com.vusachov.javapath.urlshortener;

public class StringHashGenerator implements URLHashGenerator {

    @Override
    public String getHash(String url) {
        return String.valueOf(url.hashCode());
    }
}
