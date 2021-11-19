package com.techelevator.tenmo.model.transfer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

public class NewTransferDTOTests {

    private Validator validator;

    @Before
    public void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validate_given_null_user_from_returns_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserTo(1001L);
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_null_user_to_returns_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_negative_amount_returns_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setUserTo(1002L);
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.valueOf(-1));

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_empty_transfer_type_returns_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setUserTo(1002L);
        dto.setTransferType("");
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_invalid_transfer_type_returns_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setUserTo(1002L);
        dto.setTransferType("invalid");
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_send_transfer_returns_no_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setUserTo(1002L);
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void validate_given_request_transfer_returns_no_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setUserTo(1002L);
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void validate_same_user_from_and_user_to_returns_violations() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(1001L);
        dto.setUserTo(1001L);
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.TEN);

        Set<ConstraintViolation<NewTransferDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

}
