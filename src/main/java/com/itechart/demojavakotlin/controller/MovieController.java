package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.MovieEntity;
import com.itechart.demojavakotlin.model.MovieRequest;
import com.itechart.demojavakotlin.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<MovieEntity>> getMovies() {
        return new ResponseEntity<>(movieService.getAll(), OK);
    }

    @PostMapping
    public ResponseEntity<MovieEntity> addMovie(@RequestBody final MovieRequest requestMovie) {
        return new ResponseEntity<>(movieService.addMovie(requestMovie), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable final UUID id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
