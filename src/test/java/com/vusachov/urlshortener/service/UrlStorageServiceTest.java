package com.vusachov.urlshortener.service;

import com.vusachov.urlshortener.repository.URLRepository;
import com.vusachov.urlshortener.repository.exception.URLRepositoryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UrlStorageServiceTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private URLRepository repoMock;
    private UrlStorageService storageService;

    @Before
    public void before() {
        repoMock = Mockito.mock(URLRepository.class);
        storageService = new UrlStorageService(repoMock);
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() throws URLRepositoryException {
        Mockito.when(repoMock.get(HASH)).thenReturn(ORIGIN_URL);

        storageService.put(HASH, ORIGIN_URL);

        Assert.assertEquals(ORIGIN_URL, storageService.get(HASH));
    }

    @Test
    public void returnsNullByHashIfItIsNotStored() throws URLRepositoryException {
        Mockito.when(repoMock.get("someNonInStorageHash")).thenReturn(null);

        Assert.assertNull(storageService.get("someNonInStorageHash"));

        Mockito.verify(repoMock).get("someNonInStorageHash");
    }
}
