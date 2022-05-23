package by.itechart.demojavakotlin.service.impl

import by.itechart.demojavakotlin.entity.MovieEntity
import by.itechart.demojavakotlin.model.MovieRequest
import by.itechart.demojavakotlin.repository.MovieRepository
import by.itechart.demojavakotlin.service.MovieService
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository) : MovieService {
    override fun getAll(): List<MovieEntity> = movieRepository.findAll()

    override fun addMovie(requestMovie: MovieRequest): MovieEntity = movieRepository.saveAndFlush(
        MovieEntity(
            name = requestMovie.name,
            director = requestMovie.director,
            description = requestMovie.description
        )
    )

    override fun deleteMovie(id: UUID) = movieRepository.deleteById(id)

    override fun getMovieByName(movieName: String): MovieEntity = movieRepository.findByName(movieName)
}