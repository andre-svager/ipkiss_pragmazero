package com.ebanx.account.adapter.out.persistence;

import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.application.port.out.EventRepository;
import com.ebanx.account.domain.Account;
import com.ebanx.account.domain.BankOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InMemory {

    private Map<Account, List<BankOperation>> events;

    @Repository
    public class AccountDAO implements AccountRepository {
        public void save(Account account) {
            account.setAccountId(generatePlanetId());
            events.put(account, new ArrayList());
        }

        public Optional<Account> findById(int id) {
            return events.keySet()
                                  .stream()
                                  .filter(account -> account.getAccountId() == id)
                                  .findFirst();
        }

        private int generatePlanetId() {
            return events.size()+1;
        }
    }

    @Repository
    public class EventDAO implements EventRepository {

        public void save(BankOperation event) {
            events.get(event.getAccount()).add(event);
        }

        public List<BankOperation> getAllEvents(int accountId) {
            return events.entrySet().stream()
                         .flatMap(entry -> entry.getValue()
                                                .stream().filter(ev -> ev.getAccount().getAccountId() == accountId))
                         .collect(Collectors.toList());
        }

        public Integer nextSequence(Integer id) {
            return id ++;
        }
    }
}
