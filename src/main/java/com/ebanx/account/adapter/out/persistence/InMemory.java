package com.ebanx.account.adapter.out.persistence;

import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.application.port.out.EventRepository;
import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.aggregate.BankOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
public class InMemory {

    private Map<Account, List<BankOperation>> events;

    public InMemory() {
        events = new HashMap<Account,List<BankOperation>>();
    }

    @Repository
    public class AccountDAO implements AccountRepository {
        @Override
        public void save(Account account) {
            if(!events.containsKey(account)) {
                events.put(account, new ArrayList());
            }
        }

        @Override
        public Optional<Account> findById(int id) {
            return events.keySet()
                                  .stream()
                                  .filter(account -> account.getId() == id)
                                  .findFirst();
        }

        @Override
        public boolean reset() {
            return new HashMap<Account,List<BankOperation>>().isEmpty();
        }
    }

    @Repository
    public class EventDAO implements EventRepository {

        @Override
        public void save(BankOperation event) {
            events.get(event.getDestination()).add(event);
        }
    }
}
