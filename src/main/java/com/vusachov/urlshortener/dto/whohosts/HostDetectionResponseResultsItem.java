package com.vusachov.urlshortener.dto.whohosts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostDetectionResponseResultsItem {

    private String ip;

    private String type;

    @JsonProperty("isp_id")
    private String ispId;

    @JsonProperty("isp_name")
    private String ispName;
}
