package by.itechart.demojavakotlin.exception

import org.springframework.http.HttpStatus

class EntityNotFoundException(message: String) : Exception(message) {

    val status: HttpStatus = HttpStatus.NOT_FOUND

}
