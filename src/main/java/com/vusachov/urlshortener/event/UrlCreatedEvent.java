package com.vusachov.urlshortener.event;

import com.vusachov.urlshortener.entity.Url;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UrlCreatedEvent extends ApplicationEvent {

    private Url url;

    public UrlCreatedEvent(Object source, Url url) {
        super(source);
        this.url = url;
    }
}
