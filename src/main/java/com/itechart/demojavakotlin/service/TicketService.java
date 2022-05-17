package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.TicketEntity;

import java.util.List;

public interface TicketService {

    List<TicketEntity> buyTicket(final int quantity);
}
