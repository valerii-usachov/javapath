package com.vusachov.javapath.urlshortener.storage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileURLStorage implements URLStorage {

    private final String separator = " ";
    private Path filePath;

    public FileURLStorage() {
        this("urls.txt");
    }

    public FileURLStorage(String fileName) {
        filePath = Paths.get(fileName);
    }

    @Override
    public void put(String hash, String url) {

        String existingUrl = get(hash);
        if (existingUrl != null) {
            return;
        }

        OpenOption openOption = Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE_NEW;

        try {
            String[] record = {hash + separator + url};
            Files.write(filePath, Arrays.asList(record), StandardCharsets.UTF_8, openOption);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String get(String hash) {

        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath.toString()));
        } catch (FileNotFoundException e) {
            return null;
        }

        while (scanner.hasNextLine()) {
            String[] hashUrl = scanner.nextLine().split(separator);
            String urlHash = hashUrl[0];
            String url = hashUrl[1];

            if (hash.equals(urlHash)) {
                return url;
            }
        }

        return null;
    }
}
