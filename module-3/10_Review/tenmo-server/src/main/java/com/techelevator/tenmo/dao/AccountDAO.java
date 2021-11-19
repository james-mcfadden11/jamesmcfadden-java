package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.account.Account;
import com.techelevator.tenmo.model.account.AccountNotFoundException;

public interface AccountDAO {

    Account getAccountByUserId(Long userId) throws AccountNotFoundException;

	void updateBalance(Account account);
}

