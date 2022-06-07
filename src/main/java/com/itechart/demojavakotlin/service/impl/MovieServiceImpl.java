package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.entity.TicketEntity;
import com.itechart.demojavakotlin.exceptions.EntityNotFoundException;
import com.itechart.demojavakotlin.exceptions.InvalidInputDataException;
import com.itechart.demojavakotlin.exceptions.UnprocessableException;
import com.itechart.demojavakotlin.model.MovieRequest;
import com.itechart.demojavakotlin.model.TicketsRequest;
import com.itechart.demojavakotlin.repository.MovieRepository;
import com.itechart.demojavakotlin.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    //todo #DONE
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional
    //todo #DONE
    public MovieEntity addMovie(final MovieRequest requestMovie) {
        if (requestMovie.getName().isEmpty() || requestMovie.getDirector().isEmpty()) {
            throw new InvalidInputDataException("Empty name or director fields");
        }
        return movieRepository.saveAndFlush(
                MovieEntity.builder()
                        .name(requestMovie.getName())
                        .director(requestMovie.getDirector())
                        .description(requestMovie.getDescription())
                        .ticketsQuantity(0)
                        .ticketPrice(BigDecimal.valueOf(0))
                        .build());
    }

    @Override
    @Transactional
    public void deleteMovie(final UUID id) {
        //todo #DONE
        if (!movieRepository.existsById(id)){
            throw new EntityNotFoundException(format("Movie with id: %s not found", id));
        }
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieEntity getMovieByName(final String movieName) {
        //todo #DONE
        if (movieName == null || movieName.isEmpty()) {
            throw new UnprocessableException("Movie name is null or empty");
        }
        return movieRepository.findByName(movieName).orElseThrow(
                () -> new EntityNotFoundException(format("Movie with name: %s not found", movieName)));
    }

    @Override
    @Transactional
    public void addTicketsToMovie(final TicketsRequest ticketsRequest) {
        //todo #DONE
        final MovieEntity foundMovie = getMovieByName(ticketsRequest.getMovieName());

        final int totalQuantityToUpdate = foundMovie.getTicketsQuantity() + ticketsRequest.getQuantity();

        changeTotalTicketQuantity(totalQuantityToUpdate, foundMovie.getId(), ticketsRequest.getPrice());
    }

    @Override
    @Transactional
    public void removeTicketsFromMovie(final TicketsRequest ticketsRequest) {
        //todo #DONE
        final MovieEntity foundMovie = getMovieByName(ticketsRequest.getMovieName());

        final int totalQuantityToUpdate = foundMovie.getTicketsQuantity() - ticketsRequest.getQuantity();

        changeTotalTicketQuantity(totalQuantityToUpdate, foundMovie.getId(), foundMovie.getTicketPrice());
    }

    @Override
    @Transactional
    //todo #DONE
    public void changeTotalTicketQuantity(final int quantity, final UUID id, final BigDecimal ticketPrice) {
        movieRepository.changeTotalTicketsQuantity(quantity, id, ticketPrice);
    }
}
