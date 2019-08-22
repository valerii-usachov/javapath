package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.entity.UrlHost;

import java.util.List;

public interface UrlInfoService {

    void createUrlHost(Url url, String ip, String type, String ispId, String ispName);

    List<UrlHost> getAllForUrl(Url url);
}
