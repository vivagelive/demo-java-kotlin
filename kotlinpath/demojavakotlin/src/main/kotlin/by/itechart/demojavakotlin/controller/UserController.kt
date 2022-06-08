package by.itechart.demojavakotlin.controller

import by.itechart.demojavakotlin.entity.UserEntity
import by.itechart.demojavakotlin.model.UserRequest
import by.itechart.demojavakotlin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): ResponseEntity<UserEntity> {
        return ResponseEntity<UserEntity>(userService.createUser(userRequest), HttpStatus.OK)
    }

}
