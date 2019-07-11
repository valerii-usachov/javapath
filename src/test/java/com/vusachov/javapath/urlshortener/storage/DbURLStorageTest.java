package com.vusachov.javapath.urlshortener.storage;

import com.vusachov.javapath.urlshortener.storage.repository.SqlUrlsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DbURLStorageTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private SqlUrlsRepository repoMock;
    private URLStorage storage;

    @Before
    public void before() {
        repoMock = Mockito.mock(SqlUrlsRepository.class);
        storage = new DbURLStorage(repoMock);
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() {
        Mockito.when(repoMock.getUrlByHash(HASH)).thenReturn(ORIGIN_URL);

        storage.put(HASH, ORIGIN_URL);

        Assert.assertEquals(ORIGIN_URL, storage.get(HASH));
    }

    @Test
    public void returnsNullByHashIfItIsNotStored() {
        Mockito.when(repoMock.getUrlByHash("someNonInStorageHash")).thenReturn(null);

        Assert.assertNull(storage.get("someNonInStorageHash"));

        Mockito.verify(repoMock).getUrlByHash("someNonInStorageHash");
    }
}
