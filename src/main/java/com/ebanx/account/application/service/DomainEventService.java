package com.ebanx.account.application.service;

import com.ebanx.account.domain.EventService;
import com.ebanx.account.domain.aggregate.Account;
import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.domain.aggregate.Event;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DomainEventService implements EventService {

    private final AccountRepository accountRepository;

    public DomainEventService( AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Event createEvent(Event event) {

        BigDecimal destinationBalance =
                Optional.ofNullable(event.getDestination()).isPresent() ?
                    accountRepository.getTotalBalanceByAccount(event.getDestination().getId()):BigDecimal.ZERO;

        BigDecimal originBalance =
                Optional.ofNullable(event.getOrigin()).isPresent() ?
                        accountRepository.getTotalBalanceByAccount(event.getOrigin().getId()) : BigDecimal.ZERO;

        event.applyEventToAccountOperations( destinationBalance, originBalance);

        Optional.ofNullable(event.getDestination()).ifPresent(dest -> accountRepository.save(dest));
        Optional.ofNullable(event.getOrigin()).ifPresent(origin -> accountRepository.save(origin));

        event.updateAccountBalance(destinationBalance, originBalance);
        return event;
    }

    @Override
    public Account getAccount(Integer accountId) {
        return new Account( accountRepository.findById(accountId)
                                             .orElseThrow(ResourceNotFoundException::new),
                            accountRepository.getTotalBalanceByAccount(accountId));
    }

    @Override
    public boolean reset() {
        return accountRepository.reset();
    }

}
