package com.ebanx.account.application.service;

import com.ebanx.account.application.port.out.EventRepository;
import com.ebanx.account.domain.Account;
import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.domain.BankOperation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DomainEventService implements EventService{

    private final AccountRepository accountRepository;
    private final EventRepository eventRepository;

    public DomainEventService( AccountRepository accountRepository,
                               EventRepository eventRepository) {
        this.accountRepository = accountRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public BankOperation createOperation(BankOperation operation) {
        accountRepository.findById( operation.getAccount().getId())
                         .ifPresent(acc -> updateOriginAndDestiny(operation, acc.getBalance()));

        operation.calculateBalance();
        accountRepository.save(operation.getAccount());
        eventRepository.save(operation);
        return operation;
    }

    private void updateOriginAndDestiny(BankOperation operation, BigDecimal balance) {
        operation.getAccount().updateBalance(balance);
    }

    @Override
    public Optional<Account> getAccount(Integer accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public boolean reset() {
        return accountRepository.reset();
    }

}
