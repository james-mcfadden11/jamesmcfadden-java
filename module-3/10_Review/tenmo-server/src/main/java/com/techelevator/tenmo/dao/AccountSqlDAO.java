package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class AccountSqlDAO implements AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountByUserId(Long userId) throws AccountNotFoundException {
        if(userId == null) throw new IllegalArgumentException("user_id must not be null.");

        String sql = "select account_id, user_id, balance from accounts where user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToAccount(results);
        }

        throw new AccountNotFoundException();
    }

    @Override
    public void updateBalance(Account account) {
        verifyAccountForUpdateBalance(account);

    	String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
    	jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        return new Account(rs.getLong("account_id"), rs.getLong("user_id"), rs.getBigDecimal("balance"));
    }

    // TODO: generate a 400 error
    private void verifyAccountForUpdateBalance(Account account) {
        if(account == null) {
            throw new IllegalArgumentException("account must not be null.");
        } else if (account.getAccountId() == null) {
            throw new IllegalArgumentException("account_id must not be null.");
        } else if (account.getBalance() == null) {
            throw new IllegalArgumentException("balance must not be null.");
        }
    }
}
