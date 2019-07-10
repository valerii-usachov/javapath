package com.vusachov.javapath;

import com.vusachov.javapath.urlshortener.*;
import com.vusachov.javapath.urlshortener.action.ConvertCommand;
import com.vusachov.javapath.urlshortener.db.ConnectionManager;
import com.vusachov.javapath.urlshortener.storage.DbURLStorage;
import com.vusachov.javapath.urlshortener.storage.URLStorage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static String baseURL = getProperty("baseURL");

    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager(getProps());
        URLStorage urlStorage = new DbURLStorage(connectionManager.getConnection());

        URLConverter converter = new URLConverter(baseURL, urlStorage);

        Scanner in = new Scanner(System.in);
        String commandStr = in.nextLine();
        ConvertCommand command;

        do {
            try {
                command = ConvertCommand.fromString(commandStr);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                commandStr = in.nextLine();
                continue;
            }


            String resultURL = converter.convert(command.getAction(), command.getUrl());

            System.out.println("Result URL: " + resultURL);

            commandStr = in.nextLine();
        } while (!commandStr.isEmpty());
    }

    private static String getProperty(String propName) throws IllegalArgumentException {
        Properties props = getProps();

        String prop = props.getProperty(propName);

        if (prop == null) {
            throw new IllegalArgumentException(String.format("Unknown property `%s`", propName));
        }

        return prop;
    }

    private static Properties getProps() {
        Properties props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("props.xml"))) {
            props.loadFromXML(in);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return props;
    }
}
