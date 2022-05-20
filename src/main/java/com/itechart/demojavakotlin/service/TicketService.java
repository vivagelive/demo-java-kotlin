package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.model.TicketsRequest;

import java.util.List;

public interface TicketService {

    List<TicketEntity> buyTicket(final int quantity);

    TicketEntity addTicketsToMovie(final TicketsRequest ticketsRequest);

    void removeTicketsByMovieName(final String movieName);
}
