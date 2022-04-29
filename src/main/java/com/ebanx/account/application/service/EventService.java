package com.ebanx.account.application.service;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.BankOperation;

import java.util.Optional;

public interface EventService {

    BankOperation createOperation(BankOperation operation);

    Optional<Account> getAccount(Integer accountId);

    boolean reset();
}
