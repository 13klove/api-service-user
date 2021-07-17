package com.service.api.exception

import com.service.api.domainmysql.exception.user.AlreadyUserEmailException
import com.service.api.message.error.ErrorResponse
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionController {

    companion object: KLogging()

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun paramValid(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val bindingResult = exception.bindingResult
        var errorMessage: String = ""
        if (bindingResult.hasErrors()) {
            errorMessage = bindingResult.fieldError?.defaultMessage!!
        }

        logger.info { "valid requestUrl: ${request.requestURI} | method: ${request.method} | message: $errorMessage" }
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(
                errorMessage,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                HttpStatus.BAD_REQUEST.value().toString()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(AlreadyUserEmailException::class)
    fun alreadyUserValid(
        exception: AlreadyUserEmailException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {

        logger.info { "valid requestUrl: ${request.requestURI} | method: ${request.method} | message: ${exception.message}" }
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(
                exception.message!!,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                HttpStatus.BAD_REQUEST.value().toString()
            ),
            HttpStatus.BAD_REQUEST
        )
    }

}