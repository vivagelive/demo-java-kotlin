package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.repository.TicketRepository;
import com.itechart.demojavakotlin.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Override
    public List<TicketEntity> buyTicket(final int quantity) {
        return ticketRepository.buyTicket(quantity);
    }
}
