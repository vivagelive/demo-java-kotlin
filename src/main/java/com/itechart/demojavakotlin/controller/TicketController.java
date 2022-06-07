package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/buy")
    public ResponseEntity<TicketEntity> buyTickets(@RequestParam final int quantity, @RequestParam final String movieName) {
        return new ResponseEntity<>(ticketService.buyTicket(quantity, movieName), OK);
    }
}
