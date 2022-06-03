package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.exceptions.EntityNotFoundException;
import com.itechart.demojavakotlin.exceptions.InvalidInputDataException;
import com.itechart.demojavakotlin.exceptions.UnprocessableException;
import com.itechart.demojavakotlin.model.MovieRequest;
import com.itechart.demojavakotlin.repository.MovieRepository;
import com.itechart.demojavakotlin.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional
    public MovieEntity addMovie(final MovieRequest requestMovie) {
        if (requestMovie.getName().isEmpty() || requestMovie.getDirector().isEmpty()) {
            throw new InvalidInputDataException("Empty name or director fields");
        }
        return movieRepository.saveAndFlush(
                MovieEntity.builder()
                        .name(requestMovie.getName())
                        .director(requestMovie.getDirector())
                        .description(requestMovie.getDescription())
                        .build());
    }

    @Override
    @Transactional
    public void deleteMovie(final UUID id) {
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieEntity getMovieByName(final String movieName) {
        if (movieName == null || movieName.isEmpty()) {
            throw new UnprocessableException("Movie name is null or empty");
        }
        return movieRepository.findByName(movieName).orElseThrow(
                () -> new EntityNotFoundException(String.format("Movie with name: %s not found", movieName)));
    }
}
