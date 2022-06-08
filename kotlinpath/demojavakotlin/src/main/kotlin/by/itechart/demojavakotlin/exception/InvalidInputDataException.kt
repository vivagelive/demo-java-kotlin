package by.itechart.demojavakotlin.exception

import org.springframework.http.HttpStatus

class InvalidInputDataException(message: String) : Exception(message) {

    val status: HttpStatus = HttpStatus.BAD_REQUEST

}