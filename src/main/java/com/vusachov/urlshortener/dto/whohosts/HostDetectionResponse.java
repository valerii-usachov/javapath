package com.vusachov.urlshortener.dto.whohosts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.HttpURLConnection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostDetectionResponse {

    private HostDetectionResponseResult result;

    private List<HostDetectionResponseResultsItem> results;

    public boolean isSuccess() {
        return result != null && result.getCode() == HttpURLConnection.HTTP_OK;
    }
}
