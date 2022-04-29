package com.ebanx.account.adapter.in.web;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.BankOperation;
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

    public BankOperation convertRequestToObject(){
        if(this.destination != 0){
            return new BankOperation(EventType.valueOf(this.type.trim().toUpperCase()), new Account(this.origin), new Account(this.destination), this.amount);
        }
        return new BankOperation(EventType.valueOf(this.type.trim().toUpperCase()), new Account(this.origin), this.amount);
    }
}
