package com.ebanx.account.adapter.out.persistence;

import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.domain.Account;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Component
public class InMemory {

    private Map<Integer, List<BigDecimal>> events;

    public InMemory() {
        events = new HashMap<Integer, List<BigDecimal>>();
    }

    @Repository
    public class AccountDAO implements AccountRepository {

        @Override
        public void save(Account account) {
            events.computeIfAbsent(account.getId() , ArrayList::new ).add(account.getBalance());
        }

        @Override
        public Optional<Integer> findById(int id) {
            return events.keySet()
                                  .stream()
                                  .filter(accountId -> accountId == id)
                                  .findFirst();
        }


        public BigDecimal getTotalBalanceByAccount(int id) {
             return events.entrySet().stream()
                      .filter(e -> e.getKey().compareTo(id) == 0)
                      .flatMap( entry -> entry.getValue().stream()).reduce(BigDecimal.ZERO,BigDecimal::add);
        }


        @Override
        public boolean reset() {
            return new HashMap<Integer,List<BigDecimal>>().isEmpty();
        }
    }
}
