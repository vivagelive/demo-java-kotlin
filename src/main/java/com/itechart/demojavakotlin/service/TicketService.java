package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.model.TicketsRequest;

public interface TicketService {

    TicketEntity buyTicket(final int quantity, final String movieName);

    TicketEntity addTicketsToMovie(final TicketsRequest ticketsRequest);

    void removeTicketsByMovieName(final String movieName);
}
