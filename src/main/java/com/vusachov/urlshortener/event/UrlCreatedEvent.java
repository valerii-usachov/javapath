package com.vusachov.urlshortener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UrlCreatedEvent extends ApplicationEvent {

    private Long urlId;

    public UrlCreatedEvent(Object source, Long urlId) {
        super(source);
        this.urlId = urlId;
    }
}
