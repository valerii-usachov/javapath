package com.vusachov.javapath;

import com.vusachov.javapath.urlshortener.*;
import com.vusachov.javapath.urlshortener.action.ConvertCommand;
import com.vusachov.javapath.urlshortener.hashgenerator.StringHashGenerator;
import com.vusachov.javapath.urlshortener.storage.FileURLStorage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String baseURL = getProperty("baseURL");

    public static void main(String[] args) {

        URLConverter converter = new URLConverter(baseURL, new FileURLStorage());

        Scanner in = new Scanner(System.in);
        String commandStr = in.nextLine();
        ConvertCommand command;

        do {
            try {
                command = getCommand(commandStr);
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

    private static ConvertCommand getCommand(String commandStr) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("([S|U]) ([^\\s]+)");
        Matcher matcher = pattern.matcher(commandStr);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid command format. `[S|U] Url`");
        }

        return new ConvertCommand(matcher.group(1), matcher.group(2));
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
