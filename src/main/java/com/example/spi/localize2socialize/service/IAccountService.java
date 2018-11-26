package com.example.spi.localize2socialize.service;

import com.example.spi.localize2socialize.domain.Account;

import java.util.List;

public interface IAccountService {
    Account saveOrUpdateAccount(Account account);
    List<Account> findAccounts(String personId, String filter);
    Account getAccount(String personId);
}
