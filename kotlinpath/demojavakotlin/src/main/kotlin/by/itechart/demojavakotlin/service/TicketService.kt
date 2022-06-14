package by.itechart.demojavakotlin.service

import by.itechart.demojavakotlin.entity.TicketEntity

interface TicketService {

    fun buyTicket(quantity: Int, movieName: String): TicketEntity
}