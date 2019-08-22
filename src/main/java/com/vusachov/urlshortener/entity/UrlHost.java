package com.vusachov.urlshortener.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class UrlHost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    private ISP isp;

    @NonNull
    @ManyToOne
    private Url url;

    private String ip;

    private String type;
}
