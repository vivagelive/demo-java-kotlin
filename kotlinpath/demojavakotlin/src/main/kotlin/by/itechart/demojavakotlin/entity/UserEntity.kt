package by.itechart.demojavakotlin.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
        @Id @Column(name = "id", insertable = false, updatable = false)
        private var id: UUID? = null,

        @Column(name = "money")
        private val money: BigDecimal = BigDecimal(0),

        @Column(name = "name", nullable = false)
        private val name: String? = null,

        @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        private val tickets: List<TicketEntity>? = null
)
