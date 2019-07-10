package com.vusachov.javapath.urlshortener;

import com.vusachov.javapath.urlshortener.action.ConvertAction;
import com.vusachov.javapath.urlshortener.hashgenerator.MD5HashGenerator;
import com.vusachov.javapath.urlshortener.hashgenerator.URLHashGenerator;
import com.vusachov.javapath.urlshortener.storage.InMemoryURLStorage;
import com.vusachov.javapath.urlshortener.storage.URLStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLConverter implements URLShortener {

    private String baseURL;

    private URLStorage storage;

    private URLHashGenerator hashGenerator;

    public URLConverter(String baseURL) {
        this(baseURL, new InMemoryURLStorage());
    }

    public URLConverter(String baseURL, URLStorage storage) {
        this(baseURL, storage, new MD5HashGenerator());
    }

    public URLConverter(String baseURL, URLHashGenerator hashGenerator) {
        this(baseURL);
        this.hashGenerator = hashGenerator;
    }

    public URLConverter(String baseURL, URLStorage storage, URLHashGenerator hashGenerator) {
        this.baseURL = baseURL;
        this.storage = storage;
        this.hashGenerator = hashGenerator;
    }

    public String convert(ConvertAction action, String url) {

        if (action == ConvertAction.SHORTEN) {
            return shorten(url);
        } else if (action == ConvertAction.DESHORTEN) {
            return deshorten(url);
        } else {
            return null;
        }
    }

    public String shorten(String originURL) {
        String hash = hashGenerator.getHash(originURL);
        storage.put(hash, originURL);

        return baseURL + '/' + hash;
    }

    public String deshorten(String shortURL) {
        String patternStr = escapeRE(baseURL + "/") + "(.+)";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(shortURL);

        if (!matcher.matches()) {
            return null;
        }

        String hash = matcher.group(1);

        return storage.get(hash);
    }

    private static String escapeRE(String str) {
        Pattern escaper = Pattern.compile("([^a-zA-z0-9])");

        return escaper.matcher(str).replaceAll("\\\\$1");
    }
}
