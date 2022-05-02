package com.ebanx.account.domain.aggregate;

import com.ebanx.account.domain.InsufficientBalanceException;
import com.ebanx.account.application.service.ResourceNotFoundException;
import com.ebanx.account.domain.EventType;

import java.math.BigDecimal;
import java.util.Optional;

public class Event {
    private EventType type;
    private Account origin;
    private Account destination;
    private BigDecimal amount;

    public Event(EventType type, Account origin, Account destination, BigDecimal amount) {
        this.type = type;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
    }

    public Account getDestination(){
        return this.destination;
    }

    public Account getOrigin(){ return this.origin; }

    public void applyEventToAccountOperations( BigDecimal destinationBalance,
                                               BigDecimal originBalance) {
        switch (this.type){
            case DEPOSIT ->  this.destination.updateBalance(this.amount);
            case TRANSFER -> transfer(originBalance);
            case WITHDRAW -> { if(originBalance.compareTo(BigDecimal.ZERO) ==0 ){
                                    throw new ResourceNotFoundException();
                                }
                                this.origin.negateBalance(this.amount);
            this.destination = null;}
        }
    }

    private void transfer(BigDecimal origin) {

        if(origin.compareTo(BigDecimal.ZERO) ==0 ){
            throw new ResourceNotFoundException();
        }

        Optional.ofNullable(origin.subtract(amount)
                                  .compareTo(BigDecimal.ZERO) >= 0)
                .orElseThrow(InsufficientBalanceException::new);
        this.origin.negateBalance(this.amount);
        this.destination.updateBalance(this.amount);
    }

    public void updateAccountBalance(BigDecimal destinationBalance,
                                     BigDecimal originBalance) {
        Optional.ofNullable(this.destination)
                .ifPresent(o -> o.updateBalance(destinationBalance.add(this.destination.getBalance())));
        Optional.ofNullable(this.origin)
                .ifPresent(o -> o.updateBalance(originBalance.add(this.origin.getBalance())));
    }
}
