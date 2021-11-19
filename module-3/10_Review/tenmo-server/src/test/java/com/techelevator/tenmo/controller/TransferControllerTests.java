package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.AuthorizationException;
import com.techelevator.tenmo.model.transfer.NewTransferDTO;
import com.techelevator.tenmo.model.transfer.Transfer;
import com.techelevator.tenmo.model.transfer.TransferStatusUpdateDTO;
import com.techelevator.tenmo.model.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;

@RunWith(SpringRunner.class)
public class TransferControllerTests extends BaseDaoTests {
    private static final User USER_SELF = new User(1001L, "user1", "password", "ROLE_USER");
    private static final User USER_TWO = new User(1002L, "user2", "password", "ROLE_USER");
    private static final User USER_THREE = new User(1003L, "user3", "password", "ROLE_USER");
    private static final User USER_SPY = new User(1004L, "spy", "password", "ROLE_USER");
    private static final Principal PRINCIPAL_SELF = (UserPrincipal) USER_SELF::getUsername;
    private static final Principal PRINCIPAL_USER_TWO = (UserPrincipal) USER_TWO::getUsername;
    private static final Principal PRINCIPAL_SPY = (UserPrincipal) USER_SPY::getUsername;

    private static final BigDecimal TEN_DOLLARS = BigDecimal.valueOf(1000, 2);
    private static final Transfer TRANSFER_1 = new Transfer(3001L, Transfer.TRANSFER_TYPE_SEND, Transfer.TRANSFER_STATUS_APPROVED, USER_SELF, USER_TWO, TEN_DOLLARS);

    private AccountDAO accountDAO;
    private TransferController controller;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        accountDAO = new AccountSqlDAO(jdbcTemplate);
        UserDAO userDAO = new UserSqlDAO(jdbcTemplate);
        TransferDAO transferDAO = new TransferSqlDAO(jdbcTemplate, accountDAO, userDAO);

        controller = new TransferController(transferDAO, accountDAO, userDAO);
    }

    @Test
    public void getTransfer_returns_transfer() {
        Transfer transfer = controller.getTransfer(TRANSFER_1.getTransferId(), PRINCIPAL_SELF);

        Assert.assertNotNull(transfer);
        Assert.assertEquals(TRANSFER_1, transfer);
    }

    @Test(expected = AuthorizationException.class)
    public void getTransfer_will_not_return_another_users_transfer() {
        controller.getTransfer(TRANSFER_1.getTransferId(), PRINCIPAL_SPY);
    }

    @Test
    public void getTransfers_will_only_return_my_transfers() {
        List<Transfer> myTransfers = controller.getTransfers(PRINCIPAL_SELF);

        Assert.assertNotNull(myTransfers);
        Assert.assertEquals(1, myTransfers.size());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getTransfers_throws_exception_for_invalid_user() {
        controller.getTransfers(PRINCIPAL_SPY);
    }

    @Test(expected = AuthorizationException.class)
    public void createTransfer_request_as_a_different_user_throws_exception() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_THREE.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.ONE);

        controller.createTransfer(dto, PRINCIPAL_SELF);
    }

    @Test(expected = AuthorizationException.class)
    public void createTransfer_request_on_behalf_of_someone_else_throws_exception() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_SELF.getId());
        dto.setUserTo(USER_TWO.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.ONE);

        controller.createTransfer(dto, PRINCIPAL_SELF);
    }

    @Test(expected = AuthorizationException.class)
    public void createTransfer_send_as_different_user_throws_exception() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_THREE.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.ONE);

        controller.createTransfer(dto, PRINCIPAL_SELF);
    }

    @Test(expected = AuthorizationException.class)
    public void createTransfer_send_on_behalf_of_someone_else_throws_exception() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_SELF.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.ONE);

        controller.createTransfer(dto, PRINCIPAL_SELF);
    }

    @Test
    public void createTransfer_sends_money_updates_balances() {
        BigDecimal accountSelfBalancePrior = getSelfAccountBalance();
        BigDecimal accountOtherBalancePrior = getOtherAccountBalance();

        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_SELF.getId());
        dto.setUserTo(USER_TWO.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_SEND);
        dto.setAmount(BigDecimal.ONE);

        Transfer createdTransfer = controller.createTransfer(dto, PRINCIPAL_SELF);

        Assert.assertNotNull(createdTransfer);
        Assert.assertTrue(createdTransfer.isApproved());
        Assert.assertEquals(accountSelfBalancePrior.subtract(BigDecimal.ONE), getSelfAccountBalance());
        Assert.assertEquals(accountOtherBalancePrior.add(BigDecimal.ONE), getOtherAccountBalance());
    }

    @Test
    public void createTransfer_requests_money_and_does_not_update_balance() {
        BigDecimal accountSelfBalancePrior = getSelfAccountBalance();
        BigDecimal accountOtherBalancePrior = getOtherAccountBalance();

        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_SELF.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.ONE);

        Transfer createdTransfer = controller.createTransfer(dto, PRINCIPAL_SELF);

        Assert.assertNotNull(createdTransfer);
        Assert.assertTrue(createdTransfer.isPending());
        Assert.assertEquals(accountSelfBalancePrior, getSelfAccountBalance());
        Assert.assertEquals(accountOtherBalancePrior, getOtherAccountBalance());
    }

    @Test(expected = AuthorizationException.class)
    public void updateTransferStatus_as_a_different_user_throws_exception() {
        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_SELF.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.ONE);

        Transfer createdTransfer = controller.createTransfer(dto, PRINCIPAL_SELF);

        TransferStatusUpdateDTO statusUpdateDTO = new TransferStatusUpdateDTO();
        statusUpdateDTO.setTransferStatus(Transfer.TRANSFER_STATUS_APPROVED);

        controller.updateTransferStatus(createdTransfer.getTransferId(), statusUpdateDTO, PRINCIPAL_SPY);
    }

    @Test
    public void updateTransferStatus_approves_and_transfers_money() {
        BigDecimal accountSelfBalancePrior = getSelfAccountBalance();
        BigDecimal accountOtherBalancePrior = getOtherAccountBalance();

        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_SELF.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.ONE);

        Transfer createdTransfer = controller.createTransfer(dto, PRINCIPAL_SELF);

        TransferStatusUpdateDTO statusUpdateDTO = new TransferStatusUpdateDTO();
        statusUpdateDTO.setTransferStatus(Transfer.TRANSFER_STATUS_APPROVED);

        Transfer transfer = controller.updateTransferStatus(createdTransfer.getTransferId(), statusUpdateDTO, PRINCIPAL_USER_TWO);

        Assert.assertNotNull(transfer);
        Assert.assertTrue(transfer.isApproved());
        Assert.assertEquals(accountSelfBalancePrior.add(BigDecimal.ONE), getSelfAccountBalance());
        Assert.assertEquals(accountOtherBalancePrior.subtract(BigDecimal.ONE), getOtherAccountBalance());
    }

    @Test
    public void updateTransferStatus_rejects_and_does_not_transfer_money() {
        BigDecimal accountSelfBalancePrior = getSelfAccountBalance();
        BigDecimal accountOtherBalancePrior = getOtherAccountBalance();

        NewTransferDTO dto = new NewTransferDTO();
        dto.setUserFrom(USER_TWO.getId());
        dto.setUserTo(USER_SELF.getId());
        dto.setTransferType(Transfer.TRANSFER_TYPE_REQUEST);
        dto.setAmount(BigDecimal.ONE);

        Transfer createdTransfer = controller.createTransfer(dto, PRINCIPAL_SELF);

        TransferStatusUpdateDTO statusUpdateDTO = new TransferStatusUpdateDTO();
        statusUpdateDTO.setTransferStatus(Transfer.TRANSFER_STATUS_REJECTED);

        Transfer transfer = controller.updateTransferStatus(createdTransfer.getTransferId(), statusUpdateDTO, PRINCIPAL_USER_TWO);

        Assert.assertNotNull(transfer);
        Assert.assertTrue(transfer.isRejected());
        Assert.assertEquals(accountSelfBalancePrior, getSelfAccountBalance());
        Assert.assertEquals(accountOtherBalancePrior, getOtherAccountBalance());
    }


    private BigDecimal getSelfAccountBalance() {
        return accountDAO.getAccountByUserId(USER_SELF.getId()).getBalance();
    }

    private BigDecimal getOtherAccountBalance() {
        return accountDAO.getAccountByUserId(USER_TWO.getId()).getBalance();
    }

}
