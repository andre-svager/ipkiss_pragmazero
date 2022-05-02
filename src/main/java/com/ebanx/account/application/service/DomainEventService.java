package com.ebanx.account.application.service;

import com.ebanx.account.application.port.out.EventRepository;
import com.ebanx.account.domain.Account;
import com.ebanx.account.application.port.out.AccountRepository;
import com.ebanx.account.domain.aggregate.BankOperation;
import org.springframework.stereotype.Component;

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

        updateAccountBalance(operation.getDestination().getId(), operation.getDestination());

        if(operation.getOrigin() != null){
            updateAccountBalance(operation.getOrigin().getId(), operation.getOrigin());
        }

        operation.calculateBalance();
        accountRepository.save(operation.getDestination()   );
        eventRepository.save(operation);
        return operation;
    }

    private void updateAccountBalance(Integer operation, Account account) {
        accountRepository
                .findById(operation)
                .ifPresent( acc -> account.updateCurrentBalanceWithPreviousValue(acc.getBalance()));
    }

    @Override
    public Account getAccount(Integer accountId) {
        return accountRepository.findById(accountId)
                                    .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean reset() {
        return accountRepository.reset();
    }

}
