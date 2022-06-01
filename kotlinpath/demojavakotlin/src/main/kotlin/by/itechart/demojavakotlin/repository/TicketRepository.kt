package by.itechart.demojavakotlin.repository

import by.itechart.demojavakotlin.entity.MovieEntity
import by.itechart.demojavakotlin.entity.TicketEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface TicketRepository : JpaRepository<TicketEntity, UUID> {
    @Modifying
    @Query(value = "UPDATE ticket SET quantity = quantity - :requestQuantity", nativeQuery = true)
    fun buyTicket(@Param("requestQuantity") quantity: Int): List<TicketEntity>

    @Modifying
    fun deleteByMovieId(movieId: MovieEntity?)
}