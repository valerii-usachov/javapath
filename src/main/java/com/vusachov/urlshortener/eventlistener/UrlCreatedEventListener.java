package com.vusachov.urlshortener.eventlistener;

import com.vusachov.urlshortener.dto.whohosts.HostDetectionResponse;
import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.event.UrlCreatedEvent;
import com.vusachov.urlshortener.repositories.UrlRepository;
import com.vusachov.urlshortener.service.UrlInfoService;
import com.vusachov.urlshortener.service.WhoHostsService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class UrlCreatedEventListener {

    private WhoHostsService whoHostsService;
    private UrlInfoService urlInfoService;
    private UrlRepository urlRepository;

    @Async
    @TransactionalEventListener
    public void retrieveUrlHostInfo(UrlCreatedEvent event) {
        Long urlId = event.getUrlId();
        Url url = urlRepository.findById(urlId).orElse(null);

        if (url == null) {
            return;
        }

        HostDetectionResponse hostDetectionResponse = whoHostsService.getHostDetectionInfo(url.getUrl());

        if (hostDetectionResponse == null) {
            return;
        }

        hostDetectionResponse.getResults()
                .iterator()
                .forEachRemaining((hostDetectionResponseResultsItem) -> {
                    urlInfoService.createUrlHost(
                            url,
                            hostDetectionResponseResultsItem.getIp(),
                            hostDetectionResponseResultsItem.getType(),
                            hostDetectionResponseResultsItem.getIspId(),
                            hostDetectionResponseResultsItem.getIspName()
                    );
                });

    }
}
