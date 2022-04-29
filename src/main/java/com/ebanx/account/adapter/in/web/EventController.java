package com.ebanx.account.adapter.in.web;

import com.ebanx.account.adapter.out.web.EventResponse;
import com.ebanx.account.application.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;

@RestController
@RequestMapping
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/reset")
    public ResponseEntity reset(){
        return ResponseEntity.ok(eventService.reset());
    }

    @GetMapping("/balance/{account_id}")
    public ResponseEntity<BigDecimal> getAccount(@PathParam("account_id") Integer accountId){
        //ResponseEntity.badRequest();
        return ResponseEntity.ok(eventService.getAccount(accountId).get().getBalance());
    }

    @PostMapping("/event")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest req){
        return new ResponseEntity<EventResponse>(
                new EventResponse( eventService.createOperation(req.convertRequestToObject())), HttpStatus.CREATED);
    }
}
