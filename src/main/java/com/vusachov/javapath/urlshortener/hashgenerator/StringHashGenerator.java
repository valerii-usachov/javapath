package com.vusachov.javapath.urlshortener.hashgenerator;

public class StringHashGenerator implements URLHashGenerator {

    @Override
    public String getHash(String url) {

        return String.valueOf(url.hashCode());
    }
}
