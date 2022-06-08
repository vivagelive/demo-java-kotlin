package by.itechart.demojavakotlin.exception

import org.springframework.http.HttpStatus

class UnprocessableException(message: String) : RuntimeException(message) {

    val status: HttpStatus = HttpStatus.UNPROCESSABLE_ENTITY

}
