package by.itechart.demojavakotlin.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "movie")
data class MovieEntity(
        @Id
        @Column(name = "id")
        val id: UUID = UUID.randomUUID(),

        @Column(name = "name", nullable = false, unique = true)
        val name: String,

        @Column(name = "director", nullable = false)
        val director: String,

        @Column(name = "description")
        val description: String? = null,

        @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val tickets: List<TicketEntity>? = null
)