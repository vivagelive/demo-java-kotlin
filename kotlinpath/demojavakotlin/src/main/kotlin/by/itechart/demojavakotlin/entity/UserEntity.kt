package by.itechart.demojavakotlin.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
        @Id
        @Column(name = "id", insertable = false, updatable = false)
        @GeneratedValue
        var id: UUID = UUID.randomUUID(),

        @Column(name = "money")
        val money: BigDecimal? = BigDecimal(0),

        @Column(name = "name", nullable = false)
        val name: String,

        @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val tickets: List<TicketEntity>? = null
)
