package com.ebanx.account.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class Account {

    private Integer id;
    private BigDecimal balance;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void updateCurrentBalanceWithPreviousValue(BigDecimal currentBalance){
        this.balance = this.balance.add(currentBalance);
    }

    public void deposit(BigDecimal money) {
        this.balance = this.balance.add(money);
    }

    public void withdraw(BigDecimal money) {
        if(availableBalance(money)){
            this.balance = this.balance.subtract(money);
        }
    }

    public void updateBalance(BigDecimal money) {
        this.balance = money;
    }

    public void negateBalance(BigDecimal money) {
        Optional.ofNullable(this.balance.compareTo(money) > 0)
                .orElseThrow(BalanceOutOfRangeException::new);
        this.balance = money.negate();
    }

    private boolean availableBalance(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) >= 0;
    }

}
