package com.vusachov.javapath.urlshortener.hashgenerator;

import org.junit.Assert;
import org.junit.Test;

public class StringHashGeneratorTest {

    @Test
    public void generatesCorrectStringHash() {
        URLHashGenerator hashGenerator = new StringHashGenerator();

        String hash = hashGenerator.getHash("https://java.com/");

        Assert.assertEquals("1629966635", hash);
    }
}
