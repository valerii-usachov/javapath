package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.entity.HashUrl;
import com.vusachov.urlshortener.repository.HashUrlRepository;
import com.vusachov.urlshortener.repository.exception.HashUrlRepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UrlStorageServiceTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private HashUrlRepository repoMock;
    private UrlStorageService storageService;

    @Before
    public void before() {
        repoMock = Mockito.mock(HashUrlRepository.class);
        storageService = new UrlStorageService(repoMock);
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() throws HashUrlRepositoryException {
        Mockito.when(repoMock.findOne(HASH)).thenReturn(new HashUrl(HASH, ORIGIN_URL));

        storageService.put(HASH, ORIGIN_URL);

        Assert.assertEquals(ORIGIN_URL, storageService.get(HASH));
    }

    @Test
    public void returnsNullByHashIfItIsNotStored() throws HashUrlRepositoryException {
        Mockito.when(repoMock.findOne("someNonInStorageHash")).thenReturn(null);

        Assert.assertNull(storageService.get("someNonInStorageHash"));

        Mockito.verify(repoMock).findOne("someNonInStorageHash");
    }
}
