package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.TicketEntity;

public interface TicketService {

    TicketEntity buyTicket(final int quantity, final String movieName);
}
