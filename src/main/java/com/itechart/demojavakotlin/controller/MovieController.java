package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieEntity>> getMovies() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }
}
