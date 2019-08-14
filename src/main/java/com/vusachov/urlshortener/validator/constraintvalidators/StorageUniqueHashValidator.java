package com.vusachov.urlshortener.validator.constraintvalidators;

import com.vusachov.urlshortener.service.HashUrlService;
import com.vusachov.urlshortener.validator.constraints.StorageUniqueHash;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StorageUniqueHashValidator implements ConstraintValidator<StorageUniqueHash, String> {

    private HashUrlService hashUrlService;

    @Autowired
    public StorageUniqueHashValidator(HashUrlService hashUrlService) {
        this.hashUrlService = hashUrlService;
    }

    @Override
    public boolean isValid(String hash, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (hash == null) {
            return true;
        }

        return hashUrlService.isUnique(hash);
    }
}