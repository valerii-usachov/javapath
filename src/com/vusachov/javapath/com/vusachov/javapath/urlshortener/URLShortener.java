package com.vusachov.javapath.com.vusachov.javapath.urlshortener;

public interface URLShortener {

    /**
     * @param originURL Origin full URL
     * @return Short URL
     */
    String shorten(String originURL);

    /**
     * @param shortURL Short URL
     * @return Origin full URL
     */
    String deshorten(String shortURL);

}
