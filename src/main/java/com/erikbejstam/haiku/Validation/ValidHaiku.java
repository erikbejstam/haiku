package com.erikbejstam.haiku.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = HaikuValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidHaiku {
    String message() default "The form of the haikku is invalid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}