package com.vusachov.javapath.urlshortener.action;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConvertCommandTest {

    private static final String ORIGIN_URL = "http://link.com";
    private static final String SHORTEN_URL = "http://shnt.nr/hash";

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionIfIncorrectActionAbbr() {
        new ConvertCommand("X", ORIGIN_URL);
    }

    @Test()
    public void createsValidCommandForActionAbbrS() {
        ConvertCommand command = new ConvertCommand("S", ORIGIN_URL);

        assertEquals(ConvertAction.SHORTEN, command.getAction());
        assertEquals(ORIGIN_URL, command.getUrl());
    }

    @Test()
    public void createsValidCommandForActionAbbrU() {
        ConvertCommand command = new ConvertCommand("U", SHORTEN_URL);

        assertEquals(ConvertAction.DESHORTEN, command.getAction());
        assertEquals(SHORTEN_URL, command.getUrl());
    }
}
