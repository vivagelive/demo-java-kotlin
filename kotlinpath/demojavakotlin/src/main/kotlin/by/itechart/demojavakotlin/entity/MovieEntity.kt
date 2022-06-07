package by.itechart.demojavakotlin.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "movie")
data class MovieEntity(
        @Id
        @Column(name = "id")
        @GeneratedValue
        val id: UUID = UUID.randomUUID(),

        @Column(name = "name", nullable = false, unique = true)
        val name: String,

        @Column(name = "director", nullable = false)
        val director: String,

        @Column(name = "description")
        val description: String? = null,

        @Column(name = "tickets_quantity")
        private var ticketsQuantity: Int = 0,

        @Column(name = "ticket_price")
        private val ticketPrice: BigDecimal = BigDecimal(0),

        @JsonIgnore
        @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val tickets: List<TicketEntity>? = null
)
