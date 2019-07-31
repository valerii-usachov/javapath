package com.vusachov.urlshortener.repository;

import com.vusachov.urlshortener.entity.HashUrl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryHashUrlRepositoryTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private InMemoryHashUrlRepository repository;

    @Before
    public void before() {
        repository = new InMemoryHashUrlRepository();
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() {
        repository.save(new HashUrl(HASH, ORIGIN_URL));

        HashUrl savedHash = repository.findOne(HASH);
        Assert.assertNotNull(savedHash);

        Assert.assertEquals(ORIGIN_URL, savedHash.getUrl());
    }

    @Test
    public void returnsNullByHashIfItIsNotStored() {

        repository.save(new HashUrl(HASH, ORIGIN_URL));

        Assert.assertNull(repository.findOne("someNonInStorageHash"));
    }
}
