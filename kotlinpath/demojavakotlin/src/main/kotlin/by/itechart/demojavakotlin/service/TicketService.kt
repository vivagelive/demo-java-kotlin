package by.itechart.demojavakotlin.service

import by.itechart.demojavakotlin.entity.TicketEntity
import by.itechart.demojavakotlin.model.TicketRequest

interface TicketService {

    fun buyTicket(quantity: Int): List<TicketEntity?>?

    fun addTicketsToMovie(ticketsRequest: TicketRequest): TicketEntity?

    fun removeTicketsByMovieName(movieName: String?)
}