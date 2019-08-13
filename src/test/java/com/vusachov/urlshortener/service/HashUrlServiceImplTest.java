package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.domain.Hash;
import com.vusachov.urlshortener.domain.Url;
import com.vusachov.urlshortener.exception.ResourceNotFoundException;
import com.vusachov.urlshortener.repositories.HashRepository;
import com.vusachov.urlshortener.repositories.UrlRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class HashUrlServiceImplTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private HashRepository hashRepoMock;
    private UrlRepository urlRepoMock;
    private HashUrlServiceImpl storageService;

    @Before
    public void before() {
        hashRepoMock = Mockito.mock(HashRepository.class);
        urlRepoMock = Mockito.mock(UrlRepository.class);
        storageService = new HashUrlServiceImpl(hashRepoMock, urlRepoMock);
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() {

        Optional<Hash> hashOptional = Optional.of(new Hash(new Url(ORIGIN_URL), HASH));
        Mockito.when(hashRepoMock.findByHash(HASH)).thenReturn(hashOptional);

        storageService.save(hashOptional.get());

        Assert.assertEquals(ORIGIN_URL, storageService.get(HASH).getUrl().getUrl());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void returnsNullByHashIfItIsNotStored() throws ResourceNotFoundException {
        Mockito.when(hashRepoMock.findByHash("someNonInStorageHash")).thenReturn(Optional.empty());

        storageService.get("someNonInStorageHash");

        Mockito.verify(hashRepoMock).findByHash("someNonInStorageHash");
    }
}
