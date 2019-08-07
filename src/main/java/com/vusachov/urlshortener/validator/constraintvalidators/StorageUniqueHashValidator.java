package com.vusachov.urlshortener.validator.constraintvalidators;

import com.vusachov.urlshortener.service.HashUrlStorage;
import com.vusachov.urlshortener.validator.constraints.StorageUniqueHash;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StorageUniqueHashValidator implements ConstraintValidator<StorageUniqueHash, String> {

    private HashUrlStorage hashUrlStorage;

    @Autowired
    public StorageUniqueHashValidator(HashUrlStorage hashUrlStorage) {
        this.hashUrlStorage = hashUrlStorage;
    }

    @Override
    public boolean isValid(String hash, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (hash == null) {
            return true;
        }

        return hashUrlStorage.isUnique(hash);
    }
}