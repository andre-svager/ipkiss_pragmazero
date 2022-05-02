package com.ebanx.account.application.port.out;

import com.ebanx.account.domain.aggregate.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountRepository {

    void save(Account account) ;

    Optional<Integer> findById(int id);

    boolean reset();

    public BigDecimal getTotalBalanceByAccount(int id);
}
