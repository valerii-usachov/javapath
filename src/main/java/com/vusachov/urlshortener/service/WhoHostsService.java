package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.dto.whohosts.HostDetectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nullable;
import java.net.URI;

@Service
@Slf4j
public class WhoHostsService {

    private RestTemplate restTemplate;

    private String apiKey;

    private String apiEndpoint;

    public WhoHostsService(RestTemplate restTemplate,
                           @Value("${who_hosts.api_key}") String apiKey,
                           @Value("${who_hosts.api_endpoint}") String apiEndpoint) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiEndpoint = apiEndpoint;
    }

    @Nullable
    @Cacheable("hostInfo")
    public HostDetectionResponse getHostDetectionInfo(String url) {
        URI uri = uriBuilder().queryParam("url", url).build();
        HostDetectionResponse hostDetectionResponse = null;

        try {
            hostDetectionResponse = restTemplate.getForObject(uri, HostDetectionResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
        }

        if (hostDetectionResponse == null) {
            return null;
        }

        if (!hostDetectionResponse.isSuccess()) {
            log.warn("Non success response from API: code `{}`, msg `{}`",
                    hostDetectionResponse.getResult().getCode(),
                    hostDetectionResponse.getResult().getMsg()
            );

            return null;
        }

        return hostDetectionResponse;
    }

    private UriBuilder uriBuilder() {
        UriBuilder builder = UriComponentsBuilder.fromUriString(apiEndpoint);
        builder.queryParam("key", apiKey);

        return builder;
    }
}
