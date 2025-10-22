package com.codegym.songapp.models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PhoneNumber implements Validator {

    String PhoneNumber;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Override
    public boolean supports(Class<?> clazz) { //clazz là tên của class
        return PhoneNumber.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) target;
        String number = phoneNumber.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "", "phoneNumber not empty");
        if(number.length() > 11 || number.length() < 10) {
            errors.rejectValue("phoneNumber", "", "phoneNumber length out");
        }
        if(!number.startsWith("0")) {
            errors.rejectValue("phoneNumber", "", "phoneNumber start with 0");
        }
        if(!number.matches("(^$|[0-9]*$)")) {
            errors.rejectValue("phoneNumber", "", "phoneNumber only include number");
        }
    }
}
