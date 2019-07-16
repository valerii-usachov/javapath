package com.vusachov.javapath;

import com.vusachov.javapath.urlshortener.*;
import com.vusachov.javapath.urlshortener.action.ConvertCommand;
import com.vusachov.javapath.urlshortener.repository.RepositoryFactory;
import com.vusachov.javapath.urlshortener.repository.URLRepository;
import com.vusachov.javapath.urlshortener.repository.URLRepositoryType;
import com.vusachov.javapath.urlshortener.storage.StorageService;

import java.util.Scanner;

public class Main {

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
