package com.ebanx.account.adapter.in.web;

import com.ebanx.account.domain.Account;
import com.ebanx.account.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{account_id}")
    public ResponseEntity<Account> getAccount(@PathParam("account_id") Integer accountId){
        //ResponseEntity.badRequest();

        return ResponseEntity.ok(accountService.getAccount(accountId));
    }

}
