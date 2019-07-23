package com.vusachov.urlshortener;

import com.vusachov.urlshortener.action.ConvertCommand;
import com.vusachov.urlshortener.repository.RepositoryFactory;
import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.URLRepositoryType;
import com.vusachov.urlshortener.service.StorageService;

import java.util.Scanner;

public class UrlShortenerConsoleApplication {

    private static String baseURL = Config.getProperty("baseURL");

    public static void main(String[] args) {
        URLConverter converter = new URLConverter(baseURL, getStorageService());

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

    private static StorageService getStorageService() {
        URLRepository repository = RepositoryFactory.getRepository(URLRepositoryType.DB);

        return new StorageService(repository);
    }
}
