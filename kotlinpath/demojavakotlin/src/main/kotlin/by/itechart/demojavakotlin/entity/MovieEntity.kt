package by.itechart.demojavakotlin.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "movie")
data class MovieEntity(
        @Id
        @Column(name = "id")
        val id: UUID? = null,

        @Column(name = "text", nullable = false, unique = true)
        val name: String? = null,

        @Column(name = "director", nullable = false)
        val director: String? = null,

        @Column(name = "description", nullable = false)
        val description: String? = null,

        @OneToMany(mappedBy = "movieId", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val tickets: List<TicketEntity>? = null
)