package com.vusachov.urlshortener.validator.constraintvalidators;

import com.vusachov.urlshortener.validator.constraints.URLResponseCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLResponseCodeValidator implements ConstraintValidator<URLResponseCode, String> {

    private int code;

    @Override
    public void initialize(URLResponseCode parameters) {
        code = parameters.code();
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (url == null) {
            return true;
        }

        URL u;

        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            return true;
        }

        // We want to check the current URL
        HttpURLConnection.setFollowRedirects(false);

        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection) u.openConnection();
        } catch (IOException e) {
            return true;
        }

        // Some websites don't like programmatic access so pretend to be a browser
        httpURLConnection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)"
        );

        int responseCode;
        try {
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            return false;
        }

        return responseCode == code;
    }
}