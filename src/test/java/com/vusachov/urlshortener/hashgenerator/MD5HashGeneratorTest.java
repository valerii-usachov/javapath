package com.vusachov.urlshortener.hashgenerator;

import org.junit.Assert;
import org.junit.Test;

public class MD5HashGeneratorTest {

    @Test
    public void generatesCorrectMD5Hash() {
        URLHashGenerator hashGenerator = new MD5HashGenerator();

        String hash = hashGenerator.getHash("https://java.com/");

        Assert.assertEquals("fabe8b0e0fb99105ed73ac7c498b7731", hash);
    }
}
