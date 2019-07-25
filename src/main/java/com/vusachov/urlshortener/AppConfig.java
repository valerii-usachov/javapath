package com.vusachov.urlshortener;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.vusachov"})
@PropertySource("classpath:application.properties")
public class AppConfig {

}
