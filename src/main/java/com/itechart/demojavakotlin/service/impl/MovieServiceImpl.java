package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.model.MovieRequest;
import com.itechart.demojavakotlin.repository.MovieRepository;
import com.itechart.demojavakotlin.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public MovieEntity addMovie(final MovieRequest requestMovie) {
        return movieRepository.saveAndFlush(
                MovieEntity.builder()
                        .name(requestMovie.getName())
                        .director(requestMovie.getDirector())
                        .description(requestMovie.getDescription())
                        .build());
    }

    @Override
    public void deleteMovie(final UUID id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieEntity getMovieByName(final String movieName) {
        return movieRepository.findByName(movieName);
    }
}
