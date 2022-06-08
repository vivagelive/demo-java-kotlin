package by.itechart.demojavakotlin.repository

import by.itechart.demojavakotlin.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByName(name: String): UserEntity?

    @Query(value = "SELECT money FROM users WHERE id = :id", nativeQuery = true)
    fun getUsersMoney(@Param("id") id: UUID?): BigDecimal

    @Modifying
    @Query(value = "UPDATE users SET money = money - :bill WHERE id = :id", nativeQuery = true)
    fun payTicket(@Param("id") id: UUID?, @Param("bill") bill: BigDecimal)
}