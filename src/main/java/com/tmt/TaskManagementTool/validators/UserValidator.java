package com.tmt.TaskManagementTool.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tmt.TaskManagementTool.models.User;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = new User();

        if (user.getPassword().length() < 6 && user.getPassword().length() >= 12) {
            errors.rejectValue("password", "Length", "Password must be 6-12 characters longs");
        }

        /*if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match");

        }*/
    }

    
    
}
