package com.techelevator.tenmo.model.transfer;

import java.math.BigDecimal;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewTransferDTO {

    @NotNull
    private Long userFrom;
    @NotNull
    private Long userTo;
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal amount;
    @NotEmpty
    private String transferType;

    public Long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Long userFrom) {
        this.userFrom = userFrom;
    }

    public Long getUserTo() {
        return userTo;
    }

    public void setUserTo(Long userTo) {
        this.userTo = userTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
    
    @AssertTrue(message = "Must be " + Transfer.TRANSFER_TYPE_REQUEST + " or " + Transfer.TRANSFER_TYPE_SEND)
    public boolean isValidTransferType() {
    	return Transfer.TRANSFER_TYPE_REQUEST.equals(this.transferType) || Transfer.TRANSFER_TYPE_SEND.equals(this.transferType);
    }

    @AssertTrue(message = "Must be different users")
    public boolean isUserFromDifferentThanUserTo() {
        return userFrom != null && userTo != null && !userFrom.equals(userTo);
    }

}
