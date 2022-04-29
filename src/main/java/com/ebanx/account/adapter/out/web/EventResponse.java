package com.ebanx.account.adapter.out.web;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.BankOperation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse {
    @JsonProperty(required = false)
    private Account origin;
    @JsonProperty(required = false)
    private Account destination;

    public EventResponse(BankOperation operation) {
        this.origin = operation.getAccount();
        if(operation.getDestination() !=null){
            this.destination = operation.getDestination();
        }
    }
}
