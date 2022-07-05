package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.entity.UserEntity;
import com.itechart.demojavakotlin.exceptions.NoMoneyNoHoneyException;
import com.itechart.demojavakotlin.exceptions.UnprocessableException;
import com.itechart.demojavakotlin.repository.TicketRepository;
import com.itechart.demojavakotlin.service.MovieService;
import com.itechart.demojavakotlin.service.TicketService;
import com.itechart.demojavakotlin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private static final String HOMER_SIMPSON = "Homer Simpson";
    private final UserService userService;
    private final MovieService movieService;
    private final TicketRepository ticketRepository;

    @Override
    @Transactional
    public TicketEntity buyTicket(final int quantity, final String movieName) {
        final MovieEntity foundMovie = getMovieByName(movieName);
        //todo #DONE 1.compare total tickets quantity in movie table with requested tickets
        if (foundMovie.getTicketsQuantity() < quantity) {
            throw new UnprocessableException(format("Requested tickets number greater than %d", foundMovie.getTicketsQuantity()));
        }
        //todo 2. sale
        UserEntity buyer = userService.getByName(HOMER_SIMPSON);      //mock user from db //todo auth

        final List<TicketEntity> foundTickets = ticketRepository.findByUserId(buyer);

        final int purchasedTickets =
                foundTickets.stream().map(TicketEntity::getQuantity).mapToInt(Integer::intValue).sum();

        final BigDecimal discount = valueOf((purchasedTickets < 10) ? 1 : 0.9);
        final BigDecimal finalPrice = valueOf(quantity).multiply(foundMovie.getTicketPrice().multiply(discount));

        //todo #DONE 3. change users money
        try {
            userService.payTicket(buyer.getId(), finalPrice);
        } catch (NoMoneyNoHoneyException e) {
            System.out.println("Find a job to buy this ticket");
        }

        //todo #DONE 4. change total tickets quantity
        final int updatedTickets = foundMovie.getTicketsQuantity() - quantity;
        movieService.changeTotalTicketQuantity(updatedTickets, foundMovie.getId(), foundMovie.getTicketPrice());

        PaymentInformationService.getInstance().createBill(buyer.getName(), movieName, quantity, finalPrice);

        return ticketRepository.saveAndFlush(
                TicketEntity.builder()
                        .price(finalPrice)
                        .quantity(quantity)
                        .userId(buyer)
                        .movieId(foundMovie)
                        .build());
    }


    private MovieEntity getMovieByName(final String movieName) {
        return movieService.getMovieByName(movieName);
    }
}
