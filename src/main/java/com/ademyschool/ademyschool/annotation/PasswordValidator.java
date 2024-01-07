package com.ademyschool.ademyschool.annotation;

import com.ademyschool.ademyschool.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "Please choose a strong password";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
