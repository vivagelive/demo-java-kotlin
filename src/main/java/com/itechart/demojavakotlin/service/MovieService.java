package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.model.MovieRequest;
import com.itechart.demojavakotlin.model.TicketsRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface MovieService {

    List<MovieEntity> getAll();

    MovieEntity addMovie(final MovieRequest requestMovie);

    void deleteMovie(final UUID id);

    MovieEntity getMovieByName(final String movieName);

    void changeTotalTicketQuantity(final int quantity, final UUID id, final BigDecimal ticketPrice);

    void addTicketsToMovie(final TicketsRequest ticketsRequest);

    void removeTicketsFromMovie(final TicketsRequest ticketsRequest);
}
