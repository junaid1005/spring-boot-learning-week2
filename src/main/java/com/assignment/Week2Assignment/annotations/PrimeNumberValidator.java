package com.assignment.Week2Assignment.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PrimeNumberValidator implements ConstraintValidator<PrimeNumber,Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(integer == null || integer<2) return false;
        for(int i=2;i<=(int)Math.sqrt(integer);i++){
            if(integer % i==0 ) return false;
        }
        return true;
    }
}
