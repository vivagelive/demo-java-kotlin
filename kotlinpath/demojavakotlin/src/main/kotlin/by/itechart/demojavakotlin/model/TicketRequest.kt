package by.itechart.demojavakotlin.model

import by.itechart.demojavakotlin.entity.MovieEntity
import java.math.BigDecimal

data class TicketRequest(val price: BigDecimal, val quantity: Int, val movie: MovieEntity)
