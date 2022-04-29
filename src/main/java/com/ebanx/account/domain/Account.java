package com.ebanx.account.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Integer id;
    private BigDecimal balance;

    public Account(){

    }

    public Account(Integer accountId) {
        this.id = accountId;
        this.balance = BigDecimal.ZERO;
    }

    public Account(Integer accountId, BigDecimal balance) {
        this.id = accountId;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal money) {
        updateBalance(money);
    }

    public void updateBalance(BigDecimal money){
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
