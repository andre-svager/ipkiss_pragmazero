package com.ebanx.account.domain;

import com.ebanx.account.domain.aggregate.Account;
import com.ebanx.account.domain.aggregate.Event;

public interface EventService {

    Event createEvent(Event operation);

    Account getAccount(Integer accountId);

    boolean reset();
}
