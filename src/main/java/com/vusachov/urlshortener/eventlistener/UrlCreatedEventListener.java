package com.vusachov.urlshortener.eventlistener;

import com.vusachov.urlshortener.dto.whohosts.HostDetectionResponse;
import com.vusachov.urlshortener.entity.Url;
import com.vusachov.urlshortener.event.UrlCreatedEvent;
import com.vusachov.urlshortener.service.UrlInfoService;
import com.vusachov.urlshortener.service.WhoHostsService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UrlCreatedEventListener implements ApplicationListener<UrlCreatedEvent> {

    WhoHostsService whoHostsService;
    UrlInfoService urlInfoService;

    @Override
    public void onApplicationEvent(UrlCreatedEvent event) {
        Url url = event.getUrl();

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
