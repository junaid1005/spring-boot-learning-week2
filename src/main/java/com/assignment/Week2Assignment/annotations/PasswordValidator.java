package com.assignment.Week2Assignment.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<Password,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null) return false;
        boolean length= s.length() >= 10;
        // Regular expressions
        String uppercasePattern = ".*[A-Z].*";
        String lowercasePattern = ".*[a-z].*";
        String specialCharacterPattern = ".*[!@#$%^&*(),.?\":{}|<>].*";

        boolean containsUpperCase=s.matches(uppercasePattern);
        boolean containsLowerCase=s.matches(lowercasePattern);
        boolean containsUnique=s.matches(specialCharacterPattern);

        return length && containsUnique && containsLowerCase && containsUpperCase;
    }
}
