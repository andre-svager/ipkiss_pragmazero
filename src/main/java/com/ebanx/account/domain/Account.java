package com.ebanx.account.domain;

import java.math.BigDecimal;

public class Account {

    private Integer accountId;
    private BigDecimal balance;

    public Account(){

    }

    public Account(Integer accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal money) {
        this.balance = this.balance.add(money);
    }

    public void withdraw(BigDecimal money) {
        if(availableBalance(money)){
            this.balance = this.balance.subtract(money);
        }
    }

    private boolean availableBalance(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) >= 0;
    }
}
