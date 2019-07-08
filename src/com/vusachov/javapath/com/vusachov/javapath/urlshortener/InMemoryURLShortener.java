package com.vusachov.javapath.com.vusachov.javapath.urlshortener;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InMemoryURLShortener implements URLShortener {

    private String baseURL;

    private URLHashGenerator urlHashGenerator;

    private HashMap<String, String> shortenURLs = new HashMap<>();

    public InMemoryURLShortener(String baseURL, URLHashGenerator urlHashGenerator) {

        this.baseURL = baseURL;
        this.urlHashGenerator = urlHashGenerator;
    }

    @Override
    public String shorten(String originURL) {
        String hash = urlHashGenerator.getHash(originURL);
        shortenURLs.put(hash, originURL);

        return baseURL + '/' + hash;
    }

    @Override
    public String deshorten(String shortURL) {
        String patternStr = escapeRE(baseURL + "/") + "(.+)";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(shortURL);

        if (!matcher.matches()) {
            return null;
        }

        String hash = matcher.group(1);

        return shortenURLs.get(hash);
    }

    private static String escapeRE(String str) {
        Pattern escaper = Pattern.compile("([^a-zA-z0-9])");
        return escaper.matcher(str).replaceAll("\\\\$1");
    }
}
