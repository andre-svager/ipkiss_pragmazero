package com.ebanx.account.application.port.out;

import com.ebanx.account.domain.aggregate.Event;

public interface EventRepository {

    void save(Event event);
}
