package com.ebanx.account.adapter.in.web;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.aggregate.Event;
import com.ebanx.account.domain.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class EventRequest {
    @JsonProperty
    private String type;
    @JsonProperty
    private int origin;
    @JsonProperty
    private int destination;
    @JsonProperty
    private BigDecimal amount;

    public EventRequest(String type, int origin, int destination, BigDecimal amount) {
        this.type = type;
        this.origin = origin;
        this.amount = amount;
        this.destination = destination;
    }

    public Event convertRequestToObject(){
        return new Event(EventType.valueOf(this.type.trim().toUpperCase()),
                         this.origin == 0 ? null : new Account(this.origin),
                         new Account(this.destination),
                         this.amount);
    }
}