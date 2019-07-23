package com.vusachov.urlshortener;

public interface URLShortener {

    String getHashFromOriginUrl(String originURL);

    /**
     * @param originURL Origin full URL
     * @return Short URL
     */
    String shorten(String originURL);

    String getOriginUrlByHash(String originUrl);

    /**
     * @param shortURL Short URL
     * @return Origin full URL
     */
    String deshorten(String shortURL);

}
