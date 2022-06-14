package by.itechart.demojavakotlin.repository

import by.itechart.demojavakotlin.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface MovieRepository : JpaRepository<MovieEntity, UUID> {
    fun findByName(movieName: String?): MovieEntity

    @Modifying
    @Query(value = "UPDATE movie SET tickets_quantity = :quantity, ticket_price = :ticketPrice WHERE id = :id", nativeQuery = true)
    fun changeTotalTicketsQuantity(@Param("quantity") quantity: Int,
                                   @Param("id") id: UUID,
                                   @Param("ticketPrice") ticketPrice: BigDecimal)
}
