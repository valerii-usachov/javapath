package com.vusachov.urlshortener;

import com.vusachov.urlshortener.action.ConvertCommand;
import com.vusachov.urlshortener.service.StorageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class UrlShortenerConsoleApplication {

    public static void main(String[] args) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext("com.vusachov");

        StorageService storageService = appContext.getBean("urlStorageService", StorageService.class);
        String baseUrl = appContext.getEnvironment().getProperty("base_url", String.class);

        URLConverter converter = new URLConverter(baseUrl, storageService);

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
}
