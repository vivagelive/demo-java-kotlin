package by.itechart.demojavakotlin.controller

import by.itechart.demojavakotlin.entity.MovieEntity
import by.itechart.demojavakotlin.model.MovieRequest
import by.itechart.demojavakotlin.service.MovieService
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {
    @GetMapping("/all")
    fun getMovies(): ResponseEntity<List<MovieEntity>> = ResponseEntity(movieService.getAll(), OK)

    @PostMapping
    fun addMovie(@RequestBody requestMovie: MovieRequest): ResponseEntity<MovieEntity> =
        ResponseEntity(movieService.addMovie(requestMovie), OK)

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: UUID): ResponseEntity<Void> {
        movieService.deleteMovie(id)
        return ResponseEntity(NO_CONTENT)
    }
}