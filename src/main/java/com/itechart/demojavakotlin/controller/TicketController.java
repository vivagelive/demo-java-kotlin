package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.model.TicketsRequest;
import com.itechart.demojavakotlin.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;
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

    @PostMapping
    public ResponseEntity<TicketEntity> addTicketsToMovie(final TicketsRequest ticketsRequest) {
        return new ResponseEntity<>(ticketService.addTicketsToMovie(ticketsRequest), OK);
    }

    @DeleteMapping("/{movieName}")
    public ResponseEntity<Void> removeTicketsByMovieName(@PathVariable final String movieName) {
        ticketService.removeTicketsByMovieName(movieName);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
