package com.techelevator.tenmo.model.account;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountTests {

    @Test(expected = InsufficientBalanceException.class)
    public void transfer_too_much_money_throws_exception() {
        Account accountFrom = new Account(2001L, 1001L, BigDecimal.TEN);
        Account accountTo = new Account(2002L, 1002L, BigDecimal.TEN);

        accountFrom.transfer(accountTo, BigDecimal.valueOf(11));
    }

    @Test
    public void transfer_with_sufficient_funds_updates_balance() {
        Account accountFrom = new Account(2001L, 1001L, BigDecimal.TEN);
        Account accountTo = new Account(2002L, 1002L, BigDecimal.TEN);

        accountFrom.transfer(accountTo, BigDecimal.valueOf(4));

        Assert.assertEquals(BigDecimal.valueOf(6), accountFrom.getBalance());
        Assert.assertEquals(BigDecimal.valueOf(14), accountTo.getBalance());
    }

    @Test
    public void equals_given_different_account_ids_returns_false() {
        Account account1 = new Account(2001L, 1001L, BigDecimal.TEN);
        Account account2 = new Account(2002L, 1001L, BigDecimal.TEN);

        Assert.assertNotEquals(account1, account2);
    }

    @Test
    public void equals_given_different_user_ids_returns_false() {
        Account account1 = new Account(2001L, 1001L, BigDecimal.TEN);
        Account account2 = new Account(2001L, 1002L, BigDecimal.TEN);

        Assert.assertNotEquals(account1, account2);
    }

    @Test
    public void equals_given_different_balances_returns_false() {
        Account account1 = new Account(2001L, 1001L, BigDecimal.TEN);
        Account account2 = new Account(2001L, 1001L, BigDecimal.ONE);

        Assert.assertNotEquals(account1, account2);
    }

    @Test
    public void equals_given_identical_accounts_returns_true() {
        Account account1 = new Account(2001L, 1001L, BigDecimal.TEN);
        Account account2 = new Account(2001L, 1001L, BigDecimal.TEN);

        Assert.assertEquals(account1, account2);
    }
}
