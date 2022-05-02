package com.ebanx.account.application.port.out;

import com.ebanx.account.domain.aggregate.BankOperation;

public interface EventRepository {

    void save(BankOperation event);
}
