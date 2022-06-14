package by.itechart.demojavakotlin.service

import by.itechart.demojavakotlin.entity.MovieEntity
import by.itechart.demojavakotlin.model.MovieRequest
import by.itechart.demojavakotlin.model.TicketRequest
import java.math.BigDecimal
import java.util.*

interface MovieService {
    fun getAll(): List<MovieEntity>

    fun addMovie(requestMovie: MovieRequest): MovieEntity

    fun deleteMovie(id: UUID)

    fun getMovieByName(movieName: String): MovieEntity

    fun changeTotalTicketQuantity(quantity: Int, id: UUID, ticketPrice: BigDecimal)

    fun addTicketsToMovie(ticketRequest: TicketRequest)

    fun removeTicketsFromMovie(ticketRequest: TicketRequest)
}
