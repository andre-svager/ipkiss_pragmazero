package com.ebanx.account.domain.aggregate;

import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.EventType;

import java.math.BigDecimal;

public class BankOperation {
    private EventType type;
    private Account origin;
    private Account destination;
    private BigDecimal amount;

    public BankOperation(EventType type, Account origin, Account destination, BigDecimal amount) {
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    public BankOperation(EventType type, Account destination, BigDecimal amount) {
        this.type = type;
        this.origin = destination;
        this.amount = amount;
    }

    public Account getDestination(){
        return this.destination;
    }

    public Account getOrigin(){ return this.origin; }

    public void calculateBalance( ) {
        switch (this.type){
            case DEPOSIT -> this.destination.deposit(this.amount);
            case TRANSFER -> transfer(this.amount);
            case WITHDRAW -> this.destination.withdraw(this.amount);
        }
    }

    //Change logical
    private void transfer(BigDecimal money) {
        this.destination.withdraw(money);
        this.origin.deposit(money);
    }
}
