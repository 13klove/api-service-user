package com.service.api.exception

import com.service.api.domainmysql.exception.user.AlreadyUserEmailException
import com.service.api.domainmysql.exception.user.NotFoundUserException
import com.service.api.message.error.ErrorResponse
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException
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

        return getResponseEntity(request, errorMessage)
    }

    @ExceptionHandler(AlreadyUserEmailException::class)
    fun alreadyUserValid(
        exception: AlreadyUserEmailException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return getResponseEntity(request, exception.message!!)
    }

    @ExceptionHandler(NotFoundUserException::class)
    fun notfoundUser(
        exception: NotFoundUserException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return getResponseEntity(request, exception.message!!)
    }

    @ExceptionHandler(HeaderException::class)
    fun badHeaderRequest(
        exception: HeaderException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        return getResponseEntity(request, exception.message!!)
    }

    private fun getResponseEntity(
        request: HttpServletRequest,
        message: String
    ): ResponseEntity<ErrorResponse> {
        logger.info { "valid requestUrl: ${request.requestURI} | method: ${request.method} | message: $message" }
        return ResponseEntity<ErrorResponse>(
            ErrorResponse(
                message,
                BAD_REQUEST.reasonPhrase,
                BAD_REQUEST.value().toString()
            ),
            BAD_REQUEST
        )
    }

}