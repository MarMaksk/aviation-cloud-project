package com.example.userservice.validations;

import com.example.userservice.annotation.PasswordMatches;
import com.example.userservice.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest user = (SignupRequest) o;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
