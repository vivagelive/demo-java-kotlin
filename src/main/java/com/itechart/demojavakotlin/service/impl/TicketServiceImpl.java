package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.model.TicketsRequest;
import com.itechart.demojavakotlin.repository.TicketRepository;
import com.itechart.demojavakotlin.service.MovieService;
import com.itechart.demojavakotlin.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final MovieService movieService;

    @Override
    public List<TicketEntity> buyTicket(final int quantity) {
        return ticketRepository.buyTicket(quantity);
    }

    @Override
    public TicketEntity addTicketsToMovie(final TicketsRequest ticketsRequest) {
        return ticketRepository.saveAndFlush(
                TicketEntity.builder()
                        .price(ticketsRequest.getPrice())
                        .quantity(ticketsRequest.getQuantity())
                        .build());
    }

    @Override
    public void removeTicketsByMovieName(final String movieName) {
        final MovieEntity foundMovie = movieService.getMovieByName(movieName);
        ticketRepository.deleteByMovieId(foundMovie);
    }
}
