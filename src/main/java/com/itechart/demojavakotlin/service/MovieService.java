package com.itechart.demojavakotlin.service;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.model.MovieRequest;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    List<MovieEntity> getAll();

    MovieEntity addMovie(final MovieRequest requestMovie);

    void deleteMovie(final UUID id);
}
