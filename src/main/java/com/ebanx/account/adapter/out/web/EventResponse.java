package com.ebanx.account.adapter.out.web;

import com.ebanx.account.domain.aggregate.Event;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventResponse {
    @JsonProperty(required = false)
    private AccountResponse origin;
    @JsonProperty(required = false)
    private AccountResponse destination;

    public EventResponse(Event operation) {
        Optional.ofNullable(operation.getDestination())
                .ifPresent(acc -> this.destination =
                                    new AccountResponse( acc.getId(),
                                                         acc.getBalance()));
        Optional.ofNullable(operation.getOrigin())
                .ifPresent(acc -> this.origin =
                                    new AccountResponse( acc.getId(),
                                                         acc.getBalance()));
    }
}
