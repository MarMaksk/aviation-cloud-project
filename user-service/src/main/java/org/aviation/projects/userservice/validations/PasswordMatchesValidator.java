package org.aviation.projects.userservice.validations;

import org.aviation.projects.userservice.annotation.PasswordMatches;
import org.aviation.projects.userservice.payload.request.SignupRequest;

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
