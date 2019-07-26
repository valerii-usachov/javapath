package com.vusachov.urlshortener.validator.constraintvalidators;

import com.vusachov.urlshortener.service.StorageService;
import com.vusachov.urlshortener.validator.constraints.StorageUniqueURL;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StorageUniqueURLValidator implements ConstraintValidator<StorageUniqueURL, String> {

    private StorageService storageService;

    @Autowired
    public StorageUniqueURLValidator(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (url == null) {
            return true;
        }

        return storageService.isUnique(url);
    }
}