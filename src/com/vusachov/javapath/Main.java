package com.vusachov.javapath;

import com.vusachov.javapath.com.vusachov.javapath.urlshortener.InMemoryURLShortener;
import com.vusachov.javapath.com.vusachov.javapath.urlshortener.MD5HashGenerator;
import com.vusachov.javapath.com.vusachov.javapath.urlshortener.URLHashGenerator;
import com.vusachov.javapath.com.vusachov.javapath.urlshortener.URLShortener;

public class Main {

    public static void main(String[] args) {

        String baseURL = "http://shrt.nr";
        URLHashGenerator urlHashGenerator = new MD5HashGenerator();

        URLShortener inMemoryShortener = new InMemoryURLShortener(baseURL, urlHashGenerator);

        String originURL = "https://app.pluralsight.com/paths/skill/java";

        System.out.println("Origin URL: " + originURL);
        String shortURL = inMemoryShortener.shorten(originURL);
        System.out.println("Shorten URL: " + shortURL);

        String deshortenURL = inMemoryShortener.deshorten(shortURL);
        System.out.println("Deshorten URL: " + deshortenURL);
    }
}
