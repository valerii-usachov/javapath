package com.vusachov.urlshortener;

import java.io.IOException;
import java.io.InputStream;
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

    static Properties getConfigProperties() {
        return properties;
    }

    private static Properties readProperties() {
        Properties properties = new Properties();

        try (InputStream in = Config.class.getResource("/application.properties").openStream()) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }
}
