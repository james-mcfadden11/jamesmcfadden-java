package com.techelevator.tenmo.model.auth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class RegisterUserDTOTests {

    private Validator validator;

    @Before
    public void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validate_given_null_username_returns_violations() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setPassword("a");

        Set<ConstraintViolation<RegisterUserDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_empty_username_returns_violations() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("");
        dto.setPassword("a");

        Set<ConstraintViolation<RegisterUserDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_null_password_returns_violations() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("a");

        Set<ConstraintViolation<RegisterUserDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_empty_password_returns_violations() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("a");
        dto.setPassword("");

        Set<ConstraintViolation<RegisterUserDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_valid_model_returns_no_violations() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setUsername("a");
        dto.setPassword("a");

        Set<ConstraintViolation<RegisterUserDTO>> violations = validator.validate(dto);

        Assert.assertTrue(violations.isEmpty());
    }
}
