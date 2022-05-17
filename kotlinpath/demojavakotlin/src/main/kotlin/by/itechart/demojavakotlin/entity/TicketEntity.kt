package by.itechart.demojavakotlin.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ticket")
data class TicketEntity(
        @Id
        @Column(name = "id", insertable = false, updatable = false)
        private val id: UUID? = null,

        @Column(name = "price", nullable = false)
        private val price: BigDecimal = BigDecimal(0),

        @Column(name = "quantity")
        private val quantity: Int = 0,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private val userId: UserEntity? = null,

        @ManyToOne
        @JoinColumn(name = "movie_id", nullable = false)
        private val movieId: MovieEntity? = null
)