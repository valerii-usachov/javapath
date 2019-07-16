package com.vusachov.javapath.urlshortener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private final static Properties properties = readProperties();

    public static String getProperty(String propName) throws IllegalArgumentException {

        String property = properties.getProperty(propName);

        if (property == null) {
            throw new IllegalArgumentException(String.format("Unknown property `%s`", propName));
        }

        return property;
    }

    public static Properties getConfigProperties() {
        return properties;
    }

    private static Properties readProperties() {
        Properties properties = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("config.properties"))) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }
}
