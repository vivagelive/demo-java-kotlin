package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.entity.UserEntity;
import com.itechart.demojavakotlin.exceptions.UnprocessableException;
import com.itechart.demojavakotlin.model.TicketsRequest;
import com.itechart.demojavakotlin.model.UserRequest;
import com.itechart.demojavakotlin.repository.TicketRepository;
import com.itechart.demojavakotlin.service.MovieService;
import com.itechart.demojavakotlin.service.TicketService;
import com.itechart.demojavakotlin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final UserService userService;
    private final MovieService movieService;
    private final TicketRepository ticketRepository;

    @Override
    @Transactional
    public TicketEntity buyTicket(final int quantity, final String movieName) {
        final MovieEntity foundMovie = getMovieByName(movieName);
        //todo 1.compare total tickets quantity in movie table with requested tickets
        if (foundMovie.getTicketsQuantity() < quantity) {
            throw new UnprocessableException(format("Requested tickets number greater than %d", foundMovie.getTicketsQuantity()));
        }
        //todo 2. sale
        UserEntity buyer = userService.getByName("Homer Simpson");      //mock user from db //todo auth

        final List<TicketEntity> foundTickets = ticketRepository.findByUserId(buyer);

        final int purchasedTickets =
                foundTickets.stream().map(TicketEntity::getQuantity).mapToInt(Integer::intValue).sum();

        final BigDecimal discount = valueOf((purchasedTickets < 10) ? 1 : 0.9);
        final BigDecimal finalPrice = foundMovie.getTicketPrice().multiply(discount);

        //todo 3. change users money
        userService.payTicket(buyer.getId(), finalPrice);

        return ticketRepository.saveAndFlush(
                TicketEntity.builder()
                        .price(finalPrice)
                        .quantity(quantity)
                        .userId(buyer)
                        .movieId(foundMovie)
                        .build());
    }

    @Override
    @Transactional
    public TicketEntity addTicketsToMovie(final TicketsRequest ticketsRequest) {
        final MovieEntity foundMovie = getMovieByName(ticketsRequest.getMovieName());
        //todo change total number of tickets in movie

        return ticketRepository.saveAndFlush(
                TicketEntity.builder()
                        .price(ticketsRequest.getPrice())
                        .quantity(ticketsRequest.getQuantity())
                        .build());
    }

    @Override
    @Transactional
    public void removeTicketsByMovieName(final String movieName) {
        final MovieEntity foundMovie = getMovieByName(movieName);

        ticketRepository.deleteByMovieId(foundMovie);
    }

    private MovieEntity getMovieByName(final String movieName) {
        return movieService.getMovieByName(movieName);
    }
}
