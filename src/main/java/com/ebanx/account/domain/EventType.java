package com.ebanx.account.domain;

public enum EventType {

    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    TRANSFER("transfer");

    private final String description;

    EventType(String description) {
        this.description = description;
    }
}
