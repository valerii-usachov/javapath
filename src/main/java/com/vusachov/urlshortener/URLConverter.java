package com.vusachov.urlshortener;

import com.vusachov.urlshortener.action.ConvertAction;
import com.vusachov.urlshortener.hashgenerator.MD5HashGenerator;
import com.vusachov.urlshortener.hashgenerator.URLHashGenerator;
import com.vusachov.urlshortener.service.StorageService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLConverter implements URLShortener {

    private String baseURL;

    private StorageService storage;

    private URLHashGenerator hashGenerator;

    public URLConverter(String baseURL) {
        this(baseURL, new StorageService());
    }

    public URLConverter(String baseURL, StorageService storage) {
        this(baseURL, storage, new MD5HashGenerator());
    }

    public URLConverter(String baseURL, URLHashGenerator hashGenerator) {
        this(baseURL);
        this.hashGenerator = hashGenerator;
    }

    public URLConverter(String baseURL, StorageService storage, URLHashGenerator hashGenerator) {
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

    @Override
    public String getHashFromOriginUrl(String originURL) {
        String hash = hashGenerator.getHash(originURL);
        storage.put(hash, originURL);

        return hash;
    }

    @Override
    public String shorten(String originURL) {
        String hash = hashGenerator.getHash(originURL);
        storage.put(hash, originURL);

        return baseURL + '/' + hash;
    }

    @Override
    public String getOriginUrlByHash(String hash) {
        return storage.get(hash);
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

        return storage.get(hash);
    }

    private static String escapeRE(String str) {
        Pattern escaper = Pattern.compile("([^a-zA-z0-9])");

        return escaper.matcher(str).replaceAll("\\\\$1");
    }
}
