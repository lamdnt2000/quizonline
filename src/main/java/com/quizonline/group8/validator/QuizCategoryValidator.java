package com.quizonline.group8.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class QuizCategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
