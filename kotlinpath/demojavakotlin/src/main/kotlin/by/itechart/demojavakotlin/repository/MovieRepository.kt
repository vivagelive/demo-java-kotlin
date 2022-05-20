package by.itechart.demojavakotlin.repository

import by.itechart.demojavakotlin.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MovieRepository : JpaRepository<MovieEntity, UUID> {
    fun findByName(movieName: String?): MovieEntity
}