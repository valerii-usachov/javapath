package com.vusachov.urlshortener;

import com.vusachov.urlshortener.controller.exception.ResourceNotFoundException;

public interface URLShortener {

    String getHashFromOriginUrl(String originURL);

    /**
     * @param originURL Origin full URL
     * @return Short URL
     */
    String shorten(String originURL);

    String getOriginUrlByHash(String originUrl) throws ResourceNotFoundException;

    /**
     * @param shortURL Short URL
     * @return Origin full URL
     */
    String deshorten(String shortURL);

    boolean delete(String hash);
}
