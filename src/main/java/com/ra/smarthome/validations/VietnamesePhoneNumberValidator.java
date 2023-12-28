package com.ra.smarthome.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class VietnamesePhoneNumberValidator implements ConstraintValidator<VietnamesePhoneNumber, String> {

    private static final Pattern VIETNAMESE_PHONE_NUMBER_PATTERN = Pattern.compile("^(0[35789]|01[2689])\\d{8}$");

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && VIETNAMESE_PHONE_NUMBER_PATTERN.matcher(phone).matches();
    }
}
