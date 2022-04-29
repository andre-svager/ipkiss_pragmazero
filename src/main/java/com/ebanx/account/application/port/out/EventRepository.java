package com.ebanx.account.application.port.out;

import com.ebanx.account.domain.BankOperation;

import java.util.List;

public interface EventRepository {

    void save(BankOperation event);

    List<BankOperation> getAllEvents(int accountId);
}
