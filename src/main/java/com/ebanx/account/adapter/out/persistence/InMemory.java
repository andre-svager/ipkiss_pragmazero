package com.ebanx.account.adapter.out.persistence;

import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.application.port.out.EventRepository;
import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.BankOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemory {

    private Map<Account, List<BankOperation>> events;

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
            events = new HashMap<Account,List<BankOperation>>();
            return true;
        }
    }

    @Repository
    public class EventDAO implements EventRepository {

        @Override
        public void save(BankOperation event) {
            events.get(event.getAccount()).add(event);
        }

        @Override
        public List<BankOperation> getAllEvents(int accountId) {
            return events.entrySet().stream()
                         .flatMap(entry -> entry.getValue()
                                                .stream().filter(ev -> ev.getAccount().getId() == accountId))
                         .collect(Collectors.toList());
        }
    }
}
