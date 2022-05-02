package com.ebanx.account.adapter.in.web;

import com.ebanx.account.adapter.out.web.EventResponse;
import com.ebanx.account.application.service.EventService;
import com.ebanx.account.application.service.ResourceNotFoundException;
import com.ebanx.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping
public class EventController {

    /**
     * ngrok
     * $ ./ngrok http 8080
     * Open: https://4ac2-187-18-139-147.sa.ngrok.io/
     */

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return ResponseEntity.ok("health");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset(){
        try {
            return ResponseEntity.ok(eventService.reset()? "OK":"0" );
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "0");
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getAccount(@RequestParam("account_id") Integer accountId){
        try {
            Account a = eventService.getAccount(accountId);
            return Optional.ofNullable(a).isPresent() ? ResponseEntity.ok( a.getBalance( )) : null;
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"",e);
        }
    }

    @PostMapping("/event")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest req){
        try {
            return new ResponseEntity<EventResponse>(
                    new EventResponse(eventService.createEvent(req.convertRequestToObject())),
                    HttpStatus.CREATED);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
