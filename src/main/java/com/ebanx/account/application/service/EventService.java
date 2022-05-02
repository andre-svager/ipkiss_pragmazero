package com.ebanx.account.application.service;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.aggregate.Event;

public interface EventService {

    Event createEvent(Event operation);

    Account getAccount(Integer accountId);

    boolean reset();
}
