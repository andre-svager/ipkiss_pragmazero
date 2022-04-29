package com.ebanx.account.adapter.in.web;

import com.ebanx.account.domain.BankOperation;
import com.ebanx.account.domain.EventType;

import java.math.BigDecimal;

public class EventRequest {
    private String type;
    private int origin;
    private int destination;
    private BigDecimal amount;

    public EventRequest(String type, int origin, int destination, BigDecimal amount) {
        this.type = type;
        this.origin = origin;
        this.amount = amount;
        this.destination = destination;
    }

    public BankOperation convertRequestToObject(){
        return new BankOperation();
        //return new BankOperation(EventType.valueOf(this.type), this.origin, this.destination, this.amount);
    }
}
