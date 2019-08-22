package com.vusachov.urlshortener.hashgenerator;

public class StringHashGenerator implements URLHashGenerator {

    @Override
    public String getHash(String url) {
        url = url + getRandom();

        return String.valueOf(url.hashCode());
    }
}
