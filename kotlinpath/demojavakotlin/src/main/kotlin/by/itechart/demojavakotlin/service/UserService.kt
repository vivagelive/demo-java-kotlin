package by.itechart.demojavakotlin.service

import by.itechart.demojavakotlin.entity.UserEntity
import by.itechart.demojavakotlin.model.UserRequest
import java.math.BigDecimal
import java.util.*

interface UserService {

    fun payTicket(id: UUID, bill: BigDecimal)

    fun createUser(userRequest: UserRequest): UserEntity

    fun getByName(name: String): UserEntity

}
