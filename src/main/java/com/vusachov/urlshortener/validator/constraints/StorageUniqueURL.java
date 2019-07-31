package com.vusachov.urlshortener.validator.constraints;

import com.vusachov.urlshortener.validator.constraintvalidators.StorageUniqueURLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {StorageUniqueURLValidator.class})
public @interface StorageUniqueURL {
    String message() default "Url already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}