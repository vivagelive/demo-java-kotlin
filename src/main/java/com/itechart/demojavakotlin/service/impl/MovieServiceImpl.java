package com.itechart.demojavakotlin.service.impl;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.repository.MovieRepository;
import com.itechart.demojavakotlin.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }
}
