package com.ebanx.account.application.service;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.aggregate.BankOperation;

public interface EventService {

    BankOperation createOperation(BankOperation operation);

    Account getAccount(Integer accountId);

    boolean reset();
}
