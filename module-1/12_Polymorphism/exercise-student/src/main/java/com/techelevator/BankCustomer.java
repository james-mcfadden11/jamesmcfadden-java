package com.techelevator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BankCustomer {
    private String name;
    private String address;
    private String phoneNumber;
    private List<Accountable> accounts;

    public BankCustomer() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Accountable newAccount) {
        accounts.add(newAccount);
    }

    public boolean isVip() {
        int totalAssets = 0;
        for (Accountable account : this.accounts) {
            totalAssets += account.getBalance();
        }
        return totalAssets >= 25000;
    }

    public Accountable[] getAccounts() {
        Accountable[] accountables = new Accountable[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            accountables[i] = accounts.get(i);
        }
        return accountables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
