package com.techelevator.tenmo.model.transfer;

import com.techelevator.tenmo.model.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

public class TransferAuthorizationTests {
    private final User self = new User(1L, "self", "password", "ROLE_USER");
    private final User other = new User(2L, "other", "password", "ROLE_USER");
    private final User another = new User(3L, "another", "password", "ROLE_USER");
    private final Principal principal = (UserPrincipal) self::getUsername;

    @Test
    public void isAllowedToView_another_users_transfer_returns_false() {
        Transfer transferFromOtherToAnother = new Transfer(Transfer.TRANSFER_TYPE_SEND, other, another, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromOtherToAnother);

        Assert.assertFalse(transferAuthorization.isAllowedToView());
    }

    @Test
    public void isAllowedToView_own_transfer_from_returns_true() {
        Transfer transferFromSelfToAnother = new Transfer(Transfer.TRANSFER_TYPE_SEND, self, another, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromSelfToAnother);

        Assert.assertTrue(transferAuthorization.isAllowedToView());
    }

    @Test
    public void isAllowedToView_own_transfer_to_returns_true() {
        Transfer transferFromOtherToSelf = new Transfer(Transfer.TRANSFER_TYPE_SEND, other, self, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromOtherToSelf);

        Assert.assertTrue(transferAuthorization.isAllowedToView());
    }

    @Test
    public void isAllowedToCreate_request_transfer_as_user_from_returns_false() {
        Transfer transferFromSelfToOther = new Transfer(Transfer.TRANSFER_TYPE_REQUEST, self, other, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromSelfToOther);

        Assert.assertFalse(transferAuthorization.isAllowedToCreate());
    }

    @Test
    public void isAllowedToCreate_request_transfer_as_user_to_returns_true() {
        Transfer transferFromOtherToSelf = new Transfer(Transfer.TRANSFER_TYPE_REQUEST, other, self, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromOtherToSelf);

        Assert.assertTrue(transferAuthorization.isAllowedToCreate());
    }

    @Test
    public void isAllowedToCreate_send_transfer_as_user_to_returns_false() {
        Transfer transferFromOtherToSelf = new Transfer(Transfer.TRANSFER_TYPE_SEND, other, self, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromOtherToSelf);

        Assert.assertFalse(transferAuthorization.isAllowedToCreate());
    }

    @Test
    public void isAllowedToCreate_send_transfer_as_user_from_returns_true() {
        Transfer transferFromSelfToOther = new Transfer(Transfer.TRANSFER_TYPE_SEND, self, other, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferFromSelfToOther);

        Assert.assertTrue(transferAuthorization.isAllowedToCreate());
    }

    @Test
    public void isAllowedToApproveOrReject_other_transfer_returns_false() {
        Transfer transferRequestFromOtherToAnother = new Transfer(Transfer.TRANSFER_TYPE_REQUEST, other, another, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferRequestFromOtherToAnother);

        Assert.assertFalse(transferAuthorization.isAllowedToApproveOrReject());
    }

    @Test
    public void isAllowedToApproveOrReject_my_transfer_returns_true() {
        Transfer transferRequestFromSelfToOther = new Transfer(Transfer.TRANSFER_TYPE_REQUEST, self, other, BigDecimal.TEN);

        TransferAuthorization transferAuthorization = new TransferAuthorization(principal, transferRequestFromSelfToOther);

        Assert.assertTrue(transferAuthorization.isAllowedToApproveOrReject());
    }
}
