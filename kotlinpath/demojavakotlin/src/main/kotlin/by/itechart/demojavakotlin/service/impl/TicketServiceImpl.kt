package by.itechart.demojavakotlin.service.impl

import by.itechart.demojavakotlin.entity.MovieEntity
import by.itechart.demojavakotlin.entity.TicketEntity
import by.itechart.demojavakotlin.exception.UnprocessableException
import by.itechart.demojavakotlin.repository.TicketRepository
import by.itechart.demojavakotlin.service.MovieService
import by.itechart.demojavakotlin.service.TicketService
import by.itechart.demojavakotlin.service.UserService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TicketServiceImpl(
        private val ticketRepository: TicketRepository,
        private val movieService: MovieService,
        private val userService: UserService
) : TicketService {
    override fun buyTicket(quantity: Int, movieName: String): TicketEntity {
        val foundMovie = getMovieByName(movieName)
        check(foundMovie.ticketsQuantity < quantity) {
            UnprocessableException("Requested ticket number is greater than ${foundMovie.ticketsQuantity}")
        }
        val buyer = userService.getByName("Homer Simpson")
        val foundTickets = ticketRepository.findByUserId(buyer)
        val purchasedTickets = foundTickets
            .stream()
            .map { quantity }
            .mapToInt(Int::toInt)
            .sum()

        val discount = if (purchasedTickets < 10) BigDecimal(1) else BigDecimal(0.9)
        val finalPrice = BigDecimal(quantity).multiply(foundMovie.ticketPrice).multiply(discount)

        userService.payTicket(buyer.id, finalPrice)

        val updatedTickets = foundMovie.ticketsQuantity - quantity
        movieService.changeTotalTicketQuantity(updatedTickets, foundMovie.id, foundMovie.ticketPrice)

        return ticketRepository.saveAndFlush(
            TicketEntity(
                price = finalPrice,
                quantity = quantity,
                userId = buyer,
                movieId = foundMovie
            )
        )
    }

    private fun getMovieByName(movieName: String): MovieEntity = movieService.getMovieByName(movieName)
}
