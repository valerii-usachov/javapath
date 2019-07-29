package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileSystemHashUrlRepository implements HashUrlRepository {

    private final String separator = " ";
    private Path filePath;

    public FileSystemHashUrlRepository() {
        this("urls.txt");
    }

    public FileSystemHashUrlRepository(String fileName) {
        filePath = Paths.get(fileName);
    }

    @Override
    public HashUrl save(HashUrl hashUrl) throws URLRepositoryException {

        HashUrl existingUrl = findOne(hashUrl.getHash());

        if (existingUrl != null) {
            return hashUrl;
        }

        OpenOption openOption = Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE_NEW;

        try {
            String[] record = {hashUrl.getHash() + separator + hashUrl.getUrl()};
            Files.write(filePath, Arrays.asList(record), StandardCharsets.UTF_8, openOption);
        } catch (Exception e) {
            throw new URLRepositoryException(e);
        }

        return hashUrl;
    }

    @Override
    public HashUrl findOne(String hash) throws URLRepositoryException {

        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath.toString()));
        } catch (FileNotFoundException e) {
            throw new URLRepositoryException(e);
        }

        while (scanner.hasNextLine()) {
            String[] hashUrl = scanner.nextLine().split(separator);
            String urlHash = hashUrl[0];
            String originUrl = hashUrl[1];

            if (hash.equals(urlHash)) {
                return new HashUrl(hash, originUrl);
            }
        }

        return null;
    }

    @Override
    public List<HashUrl> findAll() throws URLRepositoryException {
        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath.toString()));
        } catch (FileNotFoundException e) {
            throw new URLRepositoryException(e);
        }

        ArrayList<HashUrl> all = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] hashUrl = scanner.nextLine().split(separator);
            String hash = hashUrl[0];
            String url = hashUrl[1];

            all.add(new HashUrl(hash, url));
        }

        return all;
    }

    @Override
    public boolean delete(String hash) throws URLRepositoryException {
        throw new URLRepositoryException("Unsupported operation: delete");
    }
}
