package com.vusachov.javapath.urlshortener.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryURLStorageTest {

    private static final String HASH = "someHash";
    private static final String ORIGIN_URL = "https://java.com/";

    private InMemoryURLStorage storage;

    @Before
    public void before() {
        storage = new InMemoryURLStorage();
    }

    @Test
    public void returnsOriginalUrlByHashIfItIsStored() {
        storage.put(HASH, ORIGIN_URL);

        Assert.assertEquals(ORIGIN_URL, storage.get(HASH));
    }

    @Test
    public void returnsNullByHashIfItIsNotStored() {

        storage.put(HASH, ORIGIN_URL);

        Assert.assertNull(storage.get("someNonInStorageHash"));
    }
}
