package com.ebanx.account.application.service;

import com.ebanx.account.domain.Account;
import com.ebanx.account.application.port.out.AccountRepository;

import java.math.BigDecimal;

public class DomainAccountService implements AccountService{

    private AccountRepository accountRepository;

    @Override
    public Account getAccount(Integer accountId) {
        return new Account(1, new BigDecimal("100"));
    }



}
