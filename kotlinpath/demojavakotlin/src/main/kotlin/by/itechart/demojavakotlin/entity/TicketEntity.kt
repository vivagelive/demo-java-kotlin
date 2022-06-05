package by.itechart.demojavakotlin.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ticket")
data class TicketEntity(
        @Id
        @Column(name = "id", insertable = false, updatable = false)
        @GeneratedValue
        private val id: UUID = UUID.randomUUID(),

        @Column(name = "price", nullable = false)
        private val price: BigDecimal = BigDecimal(0),

        @Column(name = "quantity")
        private val quantity: Int = 0,

        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonIgnore
        private val userId: UserEntity? = null,

        @ManyToOne
        @JoinColumn(name = "movie_id", nullable = false)
        @JsonIgnore
        private val movieId: MovieEntity
)
