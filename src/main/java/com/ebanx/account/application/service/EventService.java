package com.ebanx.account.application.service;

import com.ebanx.account.domain.BankOperation;

public interface EventService {
    BankOperation createEvent(BankOperation convertRequestToObject);
}
