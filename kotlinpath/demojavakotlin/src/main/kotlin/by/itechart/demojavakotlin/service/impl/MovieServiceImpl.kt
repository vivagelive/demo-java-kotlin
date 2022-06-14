package by.itechart.demojavakotlin.service.impl

import by.itechart.demojavakotlin.entity.MovieEntity
import by.itechart.demojavakotlin.exception.EntityNotFoundException
import by.itechart.demojavakotlin.exception.InvalidInputDataException
import by.itechart.demojavakotlin.exception.UnprocessableException
import by.itechart.demojavakotlin.model.MovieRequest
import by.itechart.demojavakotlin.model.TicketRequest
import by.itechart.demojavakotlin.repository.MovieRepository
import by.itechart.demojavakotlin.service.MovieService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository) : MovieService {
    @Transactional(readOnly = true)
    override fun getAll(): List<MovieEntity> = movieRepository.findAll()

    @Transactional
    override fun addMovie(requestMovie: MovieRequest): MovieEntity {
        return if (requestMovie.name.isEmpty()) {
            throw InvalidInputDataException("")
        } else {
            movieRepository.saveAndFlush(
                    MovieEntity(
                            name = requestMovie.name,
                            director = requestMovie.director,
                            description = requestMovie.description
                    )
            )
        }
    }

    @Transactional
    override fun deleteMovie(id: UUID) {
        takeIf { movieRepository.existsById(id) }?.let {
            movieRepository.deleteById(id)
        } ?: throw EntityNotFoundException("Movie with id: $id is not found")
    }

    override fun getMovieByName(movieName: String): MovieEntity {
        return if (movieName.isEmpty()) {
            throw UnprocessableException("Movie name is empty")
        } else {
            movieRepository.findByName(movieName) //not necessary exception
        }
    }

    @Transactional
    override fun addTicketsToMovie(ticketRequest: TicketRequest) {
        val foundMovie = getMovieByName(ticketRequest.movieName)
        val totalQuantityToUpdate = foundMovie.ticketsQuantity + ticketRequest.quantity
        changeTotalTicketQuantity(totalQuantityToUpdate, foundMovie.id, foundMovie.ticketPrice)
    }

    override fun removeTicketsFromMovie(ticketRequest: TicketRequest) {
        val foundMovie = getMovieByName(ticketRequest.movieName)
        val totalQuantityToUpdate = foundMovie.ticketsQuantity - ticketRequest.quantity
        changeTotalTicketQuantity(totalQuantityToUpdate, foundMovie.id, foundMovie.ticketPrice)
    }

    @Transactional
    override fun changeTotalTicketQuantity(quantity: Int, id: UUID, ticketPrice: BigDecimal) =
            movieRepository.changeTotalTicketsQuantity(quantity, id, ticketPrice)
}
