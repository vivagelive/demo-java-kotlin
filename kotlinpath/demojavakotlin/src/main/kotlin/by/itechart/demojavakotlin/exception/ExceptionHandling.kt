import by.itechart.demojavakotlin.exception.EntityNotFoundException
import by.itechart.demojavakotlin.exception.InvalidInputDataException
import by.itechart.demojavakotlin.exception.UnprocessableException
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class ExceptionHandling : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handle(exception: EntityNotFoundException): ResponseEntity<Any> {
        return ResponseEntity(exception.message, HttpHeaders(), exception.status)
    }

    @ExceptionHandler
    fun handle(exception: InvalidInputDataException): ResponseEntity<Any> {
        return ResponseEntity<Any>(exception.message, HttpHeaders(), exception.status)
    }

    @ExceptionHandler
    fun handle(exception: UnprocessableException): ResponseEntity<Any> {
        return ResponseEntity<Any>(exception.message, HttpHeaders(), exception.status)
    }
}
