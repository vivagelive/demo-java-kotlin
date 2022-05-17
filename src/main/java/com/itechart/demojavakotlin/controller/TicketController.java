package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.model.TicketsRequest;
import com.itechart.demojavakotlin.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    @PutMapping("/buy")
    public ResponseEntity<List<TicketEntity>> buyTickets(@RequestBody final int quantity) {
        return new ResponseEntity<>(ticketService.buyTicket(quantity), OK);
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
