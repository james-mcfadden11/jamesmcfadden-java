package com.techelevator.tenmo.model.transfer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class TransferStatusUpdateDTOTests {
    private Validator validator;

    @Before
    public void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validate_given_null_transfer_status_returns_violations() {
        TransferStatusUpdateDTO dto = new TransferStatusUpdateDTO();

        Set<ConstraintViolation<TransferStatusUpdateDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_given_invalid_transfer_status_returns_violations() {
        TransferStatusUpdateDTO dto = new TransferStatusUpdateDTO();
        dto.setTransferStatus("invalid");

        Set<ConstraintViolation<TransferStatusUpdateDTO>> violations = validator.validate(dto);

        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void validate_approved_transfer_returns_no_violations() {
        TransferStatusUpdateDTO dto = new TransferStatusUpdateDTO();
        dto.setTransferStatus(Transfer.TRANSFER_STATUS_APPROVED);

        Set<ConstraintViolation<TransferStatusUpdateDTO>> violations = validator.validate(dto);

        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void validate_rejected_transfer_returns_no_violations() {
        TransferStatusUpdateDTO dto = new TransferStatusUpdateDTO();
        dto.setTransferStatus(Transfer.TRANSFER_STATUS_REJECTED);

        Set<ConstraintViolation<TransferStatusUpdateDTO>> violations = validator.validate(dto);

        Assert.assertTrue(violations.isEmpty());
    }
}
