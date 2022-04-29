package com.ebanx.account.adapter.in.web;

import com.ebanx.account.domain.BankOperation;
import com.ebanx.account.application.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public BankOperation sentEvent(@RequestBody EventRequest req){
        return eventService.createEvent(req.convertRequestToObject());
    }
}
