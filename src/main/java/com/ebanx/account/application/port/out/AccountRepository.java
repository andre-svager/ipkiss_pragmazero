package com.ebanx.account.application.port.out;

import com.ebanx.account.domain.Account;
import java.util.Optional;

public interface AccountRepository {

    void save(Account account) ;

    Optional<Account> findById(int id);

    boolean reset();
}
