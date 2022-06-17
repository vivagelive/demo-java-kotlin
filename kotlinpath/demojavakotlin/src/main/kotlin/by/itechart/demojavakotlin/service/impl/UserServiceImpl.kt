package by.itechart.demojavakotlin.service.impl

import by.itechart.demojavakotlin.entity.UserEntity
import by.itechart.demojavakotlin.exception.EntityNotFoundException
import by.itechart.demojavakotlin.exception.UnprocessableException
import by.itechart.demojavakotlin.model.UserRequest
import by.itechart.demojavakotlin.repository.UserRepository
import by.itechart.demojavakotlin.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun payTicket(id: UUID, bill: BigDecimal) {
        userRepository.getUsersMoney(id)
            .takeIf { it > bill } ?: throw UnprocessableException("User don't have enough money")
        userRepository.payTicket(id, bill)
    }

    @Transactional
    override fun createUser(userRequest: UserRequest): UserEntity {
        return if (userRequest.name.isEmpty())
            throw UnprocessableException("Username is empty")
        else userRepository.saveAndFlush(
            UserEntity(
                name = userRequest.name,
                money = userRequest.money
            )
        )
    }

    @Transactional
    override fun getByName(name: String): UserEntity = userRepository.findByName(name)
        ?: throw EntityNotFoundException("User not found")
}