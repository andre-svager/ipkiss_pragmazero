package com.ebanx.account.adapter.out.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AccountResponse {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private BigDecimal balance;

    public AccountResponse(Integer id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }
}