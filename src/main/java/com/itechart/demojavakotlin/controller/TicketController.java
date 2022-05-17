package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @PutMapping("/buy")
    public ResponseEntity<List<TicketEntity>> buyTickets(@RequestBody final int quantity) {
        return new ResponseEntity<>(ticketService.buyTicket(quantity), HttpStatus.OK);
    }
}
