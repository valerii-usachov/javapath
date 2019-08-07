package com.vusachov.urlshortener.validator.constraintvalidators;

import com.vusachov.urlshortener.service.StorageService;
import com.vusachov.urlshortener.validator.constraints.StorageUniqueHash;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StorageUniqueHashValidator implements ConstraintValidator<StorageUniqueHash, String> {

    private StorageService storageService;

    @Autowired
    public StorageUniqueHashValidator(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public boolean isValid(String hash, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (hash == null) {
            return true;
        }

        return storageService.isUnique(hash);
    }
}