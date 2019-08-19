package com.vusachov.urlshortener.validator.constraints;

import com.vusachov.urlshortener.validator.constraintvalidators.URLResponseCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {URLResponseCodeValidator.class})
public @interface URLResponseCode {
    String message() default "Url response code is not valid";

    int code() default HttpURLConnection.HTTP_OK;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}