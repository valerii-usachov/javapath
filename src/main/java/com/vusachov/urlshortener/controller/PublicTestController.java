package com.vusachov.urlshortener.controller;

import com.vusachov.urlshortener.dto.whohosts.HostDetectionResponse;
import com.vusachov.urlshortener.service.WhoHostsService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/test")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
final class PublicTestController {

    WhoHostsService whoHostsService;

    @GetMapping()
    HostDetectionResponse test(@RequestParam("url") String url) {
        return whoHostsService.getHostDetectionInfo(url);
    }
}