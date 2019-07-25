package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.repository.exception.URLRepositoryException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileSystemURLRepository implements URLRepository {

    private final String separator = " ";
    private Path filePath;

    public FileSystemURLRepository() {
        this("urls.txt");
    }

    public FileSystemURLRepository(String fileName) {
        filePath = Paths.get(fileName);
    }

    @Override
    public void save(String hash, String url) throws URLRepositoryException {

        String existingUrl = get(hash);
        if (existingUrl != null) {
            return;
        }

        OpenOption openOption = Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE_NEW;

        try {
            String[] record = {hash + separator + url};
            Files.write(filePath, Arrays.asList(record), StandardCharsets.UTF_8, openOption);
        } catch (Exception e) {
            throw new URLRepositoryException(e);
        }
    }

    @Override
    public String get(String hash) throws URLRepositoryException {

        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath.toString()));
        } catch (FileNotFoundException e) {
            throw new URLRepositoryException(e);
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

    @Override
    public Map<String, String> getAll() throws URLRepositoryException {
        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath.toString()));
        } catch (FileNotFoundException e) {
            throw new URLRepositoryException(e);
        }

        HashMap<String, String> all = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] hashUrl = scanner.nextLine().split(separator);
            String hash = hashUrl[0];
            String url = hashUrl[1];

            all.put(hash, url);
        }

        return all;
    }

    @Override
    public boolean delete(String hash) throws URLRepositoryException {
        throw new URLRepositoryException("Unsupported operation: delete");
    }
}
