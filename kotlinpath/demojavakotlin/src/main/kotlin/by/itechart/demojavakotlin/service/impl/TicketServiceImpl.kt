package by.itechart.demojavakotlin.service.impl

import by.itechart.demojavakotlin.entity.TicketEntity
import by.itechart.demojavakotlin.model.TicketRequest
import by.itechart.demojavakotlin.repository.TicketRepository
import by.itechart.demojavakotlin.service.MovieService
import by.itechart.demojavakotlin.service.TicketService
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository,
    private val movieService: MovieService
) : TicketService {

    override fun buyTicket(quantity: Int): List<TicketEntity> = ticketRepository.buyTicket(quantity)

    override fun addTicketsToMovie(ticketsRequest: TicketRequest): TicketEntity = ticketRepository.saveAndFlush(
        TicketEntity(quantity = ticketsRequest.quantity, price = ticketsRequest.price, movieId = ticketsRequest.movie)
    )

    override fun removeTicketsByMovieName(movieName: String) {
        movieService.getMovieByName(movieName)?.let {
            ticketRepository.deleteByMovieId(it)
        }
    }
}