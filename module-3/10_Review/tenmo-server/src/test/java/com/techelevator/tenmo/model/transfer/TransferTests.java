package com.techelevator.tenmo.model.transfer;

import com.techelevator.tenmo.model.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransferTests {

    @Test
    public void construct_request_transfer_without_status_is_pending() {
        User user1 = new User();
        User user2 = new User();
        Transfer transfer = new Transfer(Transfer.TRANSFER_TYPE_REQUEST, user1, user2, BigDecimal.TEN);

        Assert.assertEquals(Transfer.TRANSFER_STATUS_PENDING, transfer.getTransferStatus());
    }

    @Test
    public void construct_send_transfer_without_status_is_approved() {
        User user1 = new User();
        User user2 = new User();
        Transfer transfer = new Transfer(Transfer.TRANSFER_TYPE_SEND, user1, user2, BigDecimal.TEN);

        Assert.assertEquals(Transfer.TRANSFER_STATUS_APPROVED, transfer.getTransferStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct_with_invalid_status_throws_exception() {
        Long transferId = null;
        User user1 = null;
        User user2 = null;
        new Transfer(transferId, Transfer.TRANSFER_TYPE_REQUEST, "invalid", user1, user2, BigDecimal.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void construct_with_invalid_type_throws_exception() {
        Long transferId = null;
        User user1 = null;
        User user2 = null;
        new Transfer(transferId, "invalid", Transfer.TRANSFER_STATUS_APPROVED, user1, user2, BigDecimal.TEN);
    }

    @Test(expected = InvalidTransferStatusUpdateException.class)
    public void approve_approved_transfer_throws_exception() {
        Long transferId = null;
        User user1 = new User();
        User user2 = new User();
        Transfer approvedTransfer = new Transfer(transferId, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, user1, user2, BigDecimal.TEN);

        approvedTransfer.approve();
    }

    @Test(expected = InvalidTransferStatusUpdateException.class)
    public void approve_rejected_transfer_throws_exception() {
        Long transferId = null;
        User user1 = new User();
        User user2 = new User();
        Transfer rejectedTransfer = new Transfer(transferId, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_REJECTED, user1, user2, BigDecimal.TEN);

        rejectedTransfer.approve();
    }

    @Test
    public void approve_pending_transfer_changes_status() {
        Long transferId = null;
        User user1 = new User();
        User user2 = new User();
        Transfer transfer = new Transfer(transferId, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_PENDING, user1, user2, BigDecimal.TEN);

        transfer.approve();

        Assert.assertFalse(transfer.isPending());
        Assert.assertTrue(transfer.isApproved());
    }

    @Test(expected = InvalidTransferStatusUpdateException.class)
    public void reject_approved_transfer_throws_exception() {
        Long transferId = null;
        User user1 = new User();
        User user2 = new User();
        Transfer transfer = new Transfer(transferId, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, user1, user2, BigDecimal.TEN);

        transfer.reject();
    }

    @Test(expected = InvalidTransferStatusUpdateException.class)
    public void reject_rejected_transfer_throws_exception() {
        Long transferId = null;
        User user1 = new User();
        User user2 = new User();
        Transfer transfer = new Transfer(transferId, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_REJECTED, user1, user2, BigDecimal.TEN);

        transfer.reject();
    }

    @Test
    public void reject_pending_transfer_changes_status() {
        Long transferId = null;
        User user1 = new User();
        User user2 = new User();
        Transfer transfer = new Transfer(transferId, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_PENDING, user1, user2, BigDecimal.TEN);

        transfer.reject();

        Assert.assertFalse(transfer.isPending());
        Assert.assertTrue(transfer.isRejected());
    }

    @Test
    public void equals_given_different_ids_returns_false() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userTo = new User(2L, "user2", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);
        Transfer transfer2 = new Transfer(-2L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);

        Assert.assertNotEquals(transfer1, transfer2);
    }

    @Test
    public void equals_given_different_types_returns_false() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userTo = new User(2L, "user2", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_REQUEST, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);
        Transfer transfer2 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);

        Assert.assertNotEquals(transfer1, transfer2);
    }

    @Test
    public void equals_given_different_statuses_returns_false() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userTo = new User(2L, "user2", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_REJECTED, userFrom, userTo, BigDecimal.TEN);
        Transfer transfer2 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);

        Assert.assertNotEquals(transfer1, transfer2);
    }

    @Test
    public void equals_given_different_amounts_returns_false() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userTo = new User(2L, "user2", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.ONE);
        Transfer transfer2 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);

        Assert.assertNotEquals(transfer1, transfer2);
    }

    @Test
    public void equals_given_different_user_from_returns_false() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userFrom2 = new User(2L, "user2", "password", "ROLE_USER");
        User userTo = new User(3L, "user3", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);
        Transfer transfer2 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom2, userTo, BigDecimal.TEN);

        Assert.assertNotEquals(transfer1, transfer2);
    }

    @Test
    public void equals_given_different_user_to_returns_false() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userTo = new User(2L, "user2", "password", "ROLE_USER");
        User userTo2 = new User(3L, "user3", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);
        Transfer transfer2 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo2, BigDecimal.TEN);

        Assert.assertNotEquals(transfer1, transfer2);
    }

    @Test
    public void equals_returns_true() {
        User userFrom = new User(1L, "user", "password", "ROLE_USER");
        User userTo = new User(2L, "user2", "password", "ROLE_USER");
        Transfer transfer1 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);
        Transfer transfer2 = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, userFrom, userTo, BigDecimal.TEN);

        Assert.assertEquals(transfer1, transfer2);
    }
}
