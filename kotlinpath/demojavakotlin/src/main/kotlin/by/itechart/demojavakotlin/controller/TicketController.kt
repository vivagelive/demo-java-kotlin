package by.itechart.demojavakotlin.controller

import by.itechart.demojavakotlin.entity.TicketEntity
import by.itechart.demojavakotlin.model.TicketRequest
import by.itechart.demojavakotlin.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tickets")
class TicketController(private val ticketService: TicketService) {
    @PutMapping("/buy")
    fun buyTickets(@RequestBody quantity: Int): ResponseEntity<List<TicketEntity>> {
        return ResponseEntity<List<TicketEntity>>(ticketService.buyTicket(quantity), HttpStatus.OK)
    }

    @PostMapping
    fun addTicketsToMovie(ticketsRequest: TicketRequest): ResponseEntity<TicketEntity> {
        return ResponseEntity<TicketEntity>(ticketService.addTicketsToMovie(ticketsRequest), HttpStatus.OK)
    }

    @DeleteMapping("/{movieName}")
    fun removeTicketsByMovieName(@PathVariable movieName: String): ResponseEntity<Void> {
        ticketService.removeTicketsByMovieName(movieName)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}