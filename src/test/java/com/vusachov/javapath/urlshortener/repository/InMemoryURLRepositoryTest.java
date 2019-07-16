package com.vusachov.javapath.urlshortener.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryURLRepositoryTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private InMemoryURLRepository repository;

    @Before
    public void before() {
        repository = new InMemoryURLRepository();
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() {
        repository.save(HASH, ORIGIN_URL);

        Assert.assertEquals(ORIGIN_URL, repository.get(HASH));
    }

    @Test
    public void returnsNullByHashIfItIsNotStored() {

        repository.save(HASH, ORIGIN_URL);

        Assert.assertNull(repository.get("someNonInStorageHash"));
    }
}
