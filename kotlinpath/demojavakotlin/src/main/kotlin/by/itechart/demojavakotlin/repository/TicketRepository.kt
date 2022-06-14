package by.itechart.demojavakotlin.repository

import by.itechart.demojavakotlin.entity.TicketEntity
import by.itechart.demojavakotlin.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TicketRepository : JpaRepository<TicketEntity, UUID> {
    fun findByUserId(user: UserEntity): List<TicketEntity>

}