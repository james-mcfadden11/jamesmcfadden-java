package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class AccountSqlDaoTests extends BaseDaoTests {
    protected static final BigDecimal ONE_THOUSAND_DOLLARS = BigDecimal.valueOf(100000, 2);
    private static final Account ACCOUNT_1 = new Account(2001L, 1001L, ONE_THOUSAND_DOLLARS);

    private AccountSqlDAO sut;

    @Before
    public void setup() {
        sut = new AccountSqlDAO(new JdbcTemplate(dataSource));
    }

    @Test(expected = AccountNotFoundException.class)
    public void getAccountByUserId_for_invalid_user_throws_exception() {
        sut.getAccountByUserId(-1L);
    }

    @Test
    public void getAccountByUserId_returns_correct_account() {
        Account actualAccount = sut.getAccountByUserId(ACCOUNT_1.getUserId());

        Assert.assertEquals(ACCOUNT_1, actualAccount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateBalance_given_null_account() {
        sut.updateBalance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateBalance_given_null_account_id() {
        Account invalidAccount = new Account(null, 1001L, ONE_THOUSAND_DOLLARS);

        sut.updateBalance(invalidAccount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateBalance_given_null_balance() {
        Account invalidAccount = new Account(2001L, 1001L, null);

        sut.updateBalance(invalidAccount);
    }

    @Test
    public void updateBalance_given_account_that_does_not_exist() {
        // TODO
    }

    @Test
    public void updateBalance_changes_account_balance() {
        Account updatedAccount = new Account(ACCOUNT_1.getAccountId(), ACCOUNT_1.getUserId(), ONE_THOUSAND_DOLLARS.add(BigDecimal.TEN));

        sut.updateBalance(updatedAccount);

        Account actualAccount = sut.getAccountByUserId(ACCOUNT_1.getUserId());
        Assert.assertEquals(updatedAccount, actualAccount);
    }
}
