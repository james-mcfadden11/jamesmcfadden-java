package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.transfer.Transfer;
import com.techelevator.tenmo.model.transfer.TransferNotFoundException;
import com.techelevator.tenmo.model.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class TransferSqlDaoTests extends BaseDaoTests {
    private static final BigDecimal TEN_DOLLARS = BigDecimal.valueOf(1000, 2);
    private static final Transfer TRANSFER_1 = new Transfer(3001L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_2, TEN_DOLLARS);

    private TransferSqlDAO sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        AccountSqlDAO accountSqlDAO = new AccountSqlDAO(jdbcTemplate);
        UserSqlDAO userSqlDAO = new UserSqlDAO(jdbcTemplate);
        sut = new TransferSqlDAO(jdbcTemplate, accountSqlDAO, userSqlDAO);
    }

    @Test(expected = TransferNotFoundException.class)
    public void getTransferById_given_null_transfer_id_throws_exception() {
        sut.getTransferById(null);
    }

    @Test(expected = TransferNotFoundException.class)
    public void getTransferById_given_invalid_transfer_id_throws_exception() {
        sut.getTransferById(-1L);
    }

    @Test
    public void getTransferById_given_valid_transfer_id_returns_transfer() {
        Transfer actualTransfer = sut.getTransferById(TRANSFER_1.getTransferId());

        Assert.assertEquals(TRANSFER_1, actualTransfer);
    }

    @Test
    public void findAll_returns_transfers() {
        List<Transfer> transfers = sut.findAll();

        Assert.assertNotNull(transfers);
        Assert.assertEquals(1, transfers.size());
        Assert.assertEquals(TRANSFER_1, transfers.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_null_transfer_type_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, null, Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_1, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_invalid_transfer_type_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, "invalid", Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_1, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_null_transfer_status_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, Transfer.TRANSFER_TYPE_SEND, null, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_1, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_invalid_transfer_status_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, Transfer.TRANSFER_TYPE_SEND, "invalid", UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_1, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_null_account_from_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, null, UserSqlDaoTests.USER_1, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_null_account_to_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_1, null, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addTransfer_with_matching_account_from_and_account_to_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_1, BigDecimal.valueOf(100));

        sut.addTransfer(invalidTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTransfer_with_negative_amount_throws_exception() {
        Transfer invalidTransfer = new Transfer(1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_2, BigDecimal.valueOf(-100));

        sut.addTransfer(invalidTransfer);
    }

    @Test
    public void addTransfer_creates_approved_send_transfer() {
        Transfer transfer = new Transfer(-1L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, UserSqlDaoTests.USER_2, UserSqlDaoTests.USER_1, TEN_DOLLARS);

        Transfer actualTransfer = sut.addTransfer(transfer);
        transfer.setTransferId(actualTransfer.getTransferId());

        Assert.assertEquals(transfer, actualTransfer);
    }

    @Test
    public void addTransfer_creates_pending_request_transfer() {
        Transfer transfer = new Transfer(-1L, Transfer.TRANSFER_TYPE_REQUEST, Transfer.TRANSFER_STATUS_PENDING, UserSqlDaoTests.USER_2, UserSqlDaoTests.USER_1, TEN_DOLLARS);

        Transfer actualTransfer = sut.addTransfer(transfer);
        transfer.setTransferId(actualTransfer.getTransferId());

        Assert.assertEquals(transfer, actualTransfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateStatus_null_transfer_status_throws_exception() {
        String transferStatus = null;
        User userFrom = new User();
        User userTo = new User();
        BigDecimal amount = BigDecimal.ONE;
        Transfer transfer = new Transfer(3005L, Transfer.TRANSFER_TYPE_SEND, transferStatus, userFrom, userTo, amount);

        sut.updateStatus(transfer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateStatus_invalid_transfer_status_throws_exception() {
        String transferStatus = "invalid";
        User userFrom = new User();
        User userTo = new User();
        BigDecimal amount = BigDecimal.ONE;
        Transfer transfer = new Transfer(3005L, Transfer.TRANSFER_TYPE_SEND, transferStatus, userFrom, userTo, amount);


        sut.updateStatus(transfer);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getPendingTransfersForUser_for_null_user_throws_exception() {
        sut.getPendingTransfersForUser(null);
    }

    @Test
    public void getPendingTransfersForUser_for_invalid_user_returns_empty_list() {
        List<Transfer> pendingTransfers = sut.getPendingTransfersForUser(-1L);

        Assert.assertNotNull(pendingTransfers);
        Assert.assertEquals(0, pendingTransfers.size());
    }

    @Test
    public void getPendingTransfersForUser_given_valid_user_returns_pending_transfers() {
        Transfer TRANSFER_2 = new Transfer(3002L, Transfer.TRANSFER_TYPE_REQUEST, Transfer.TRANSFER_STATUS_PENDING, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_2, TEN_DOLLARS);
        Transfer TRANSFER_3 = new Transfer(3003L, Transfer.TRANSFER_TYPE_REQUEST, Transfer.TRANSFER_STATUS_PENDING, UserSqlDaoTests.USER_1, UserSqlDaoTests.USER_2, TEN_DOLLARS);

        Transfer addedTransfer = sut.addTransfer(TRANSFER_2);
        TRANSFER_2.setTransferId(addedTransfer.getTransferId());

        addedTransfer = sut.addTransfer(TRANSFER_3);
        TRANSFER_3.setTransferId(addedTransfer.getTransferId());


        List<Transfer> pendingTransfers = sut.getPendingTransfersForUser(1001L);

        Assert.assertNotNull(pendingTransfers);
        Assert.assertEquals(2, pendingTransfers.size());
        Assert.assertEquals(TRANSFER_2, pendingTransfers.get(0));
        Assert.assertEquals(TRANSFER_3, pendingTransfers.get(1));

    }
}
