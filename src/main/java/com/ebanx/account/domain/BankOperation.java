package com.ebanx.account.domain;

import java.math.BigDecimal;

public class BankOperation {
    private EventType type;
    private Account origin;
    private Account destination;
    private BigDecimal amount;

    public BankOperation(){}

    public BankOperation(EventType type, Account origin, Account destination, BigDecimal amount) {
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    public BankOperation(EventType type, Account origin, BigDecimal amount) {
        this.type = type;
        this.origin = origin;
        this.amount = amount;
    }

    public Account getAccount(){
        return this.origin;
    }

    public void calculateBalanceAfterEvent(BigDecimal money) {
        switch (this.type){
            case DEPOSIT -> this.origin.deposit(money);
            case TRANSFER -> transfer(money);
            case WITHDRAW -> this.origin.withdraw(money);
        }
    }

    private void transfer(BigDecimal money) {
        this.origin.withdraw(money);
        this.destination.deposit(money);
    }
}
