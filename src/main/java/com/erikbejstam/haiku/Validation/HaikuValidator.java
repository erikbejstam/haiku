package com.erikbejstam.haiku.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HaikuValidator implements ConstraintValidator<ValidHaiku, String> {
    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("\n");
        Matcher matcher = pattern.matcher(text);
        int matches = 0;

        while (matcher.find()) {
            matches++;
        }

        return matches == 2;
    }
}
