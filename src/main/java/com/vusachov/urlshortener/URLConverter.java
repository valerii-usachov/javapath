package com.vusachov.urlshortener;

import com.vusachov.urlshortener.controller.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.hashgenerator.MD5HashGenerator;
import com.vusachov.urlshortener.hashgenerator.URLHashGenerator;
import com.vusachov.urlshortener.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class URLConverter implements URLShortener {

    private String baseUrl;

    private StorageService storageService;

    private URLHashGenerator hashGenerator;

    @Autowired
    public URLConverter(@Value("${base_url}") String baseUrl, StorageService storageService) {
        this(baseUrl, storageService, new MD5HashGenerator());
    }

    public URLConverter(String baseURL, StorageService storageService, URLHashGenerator hashGenerator) {
        this.baseUrl = baseURL;
        this.storageService = storageService;
        this.hashGenerator = hashGenerator;
    }

    @Override
    public String getHashFromOriginUrl(String originURL) {
        String hash = hashGenerator.getHash(originURL);
        storageService.put(hash, originURL);

        return hash;
    }

    @Override
    public String shorten(String originURL) {
        String hash = hashGenerator.getHash(originURL);
        storageService.put(hash, originURL);

        return baseUrl + '/' + hash;
    }

    @Override
    public String getOriginUrlByHash(String hash) throws ResourceNotFoundException {
        String originUrl = storageService.get(hash);

        if (originUrl == null) {
            throw new ResourceNotFoundException();
        }

        return originUrl;
    }

    @Override
    public String deshorten(String shortURL) {
        String patternStr = escapeRE(baseUrl + "/") + "(.+)";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(shortURL);

        if (!matcher.matches()) {
            return null;
        }

        String hash = matcher.group(1);

        return storageService.get(hash);
    }

    @Override
    public boolean delete(String hash) {
        return storageService.delete(hash);
    }

    private static String escapeRE(String str) {
        Pattern escaper = Pattern.compile("([^a-zA-z0-9])");

        return escaper.matcher(str).replaceAll("\\\\$1");
    }
}
