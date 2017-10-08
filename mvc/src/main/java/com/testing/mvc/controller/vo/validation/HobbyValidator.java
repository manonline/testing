package com.testing.mvc.controller.vo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbyValidator implements ConstraintValidator<IsValidHobby, String> {

    private String listOfValidHobbies;

    @Override
    public void initialize(IsValidHobby isValidHobby) {
        this.listOfValidHobbies = isValidHobby.listOfValidHobbies();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        if (s.matches(listOfValidHobbies)) {
            return true;
        }

        return false;
    }
}
