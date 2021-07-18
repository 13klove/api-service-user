package com.service.api.handler

import com.service.api.annotaion.HeaderValid
import com.service.api.exception.HeaderException
import mu.KLogging
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestHeaderHandler: HandlerInterceptor {

    companion object: KLogging() {
        const val HEADER_EXPTION_MESSAGE = "bad request"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info { "handler - HandlerMethod: ${handler is HandlerMethod}" }

        if (handler is HandlerMethod) {
            val headerValid = handler.getMethodAnnotation(HeaderValid::class.java)
            headerValid?.run {
                this.headers.forEach {
                    request.getHeader(it)?: throw HeaderException(HEADER_EXPTION_MESSAGE)
                }
            }
        }

        return true
    }
}