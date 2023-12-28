package com.ra.smarthome.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VietnamesePhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VietnamesePhoneNumber {
    String message() default "Invalid Vietnamese phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
