package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.response.ErrorResponseDto
import io.github.bsfranca2.athena.exception.EntityNotFoundException
import io.github.bsfranca2.athena.exception.UnauthorizedResourceException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime

object ExceptionAdapter {

    fun toResponse(ex: MethodArgumentTypeMismatchException): ErrorResponseDto {
        val status = HttpStatus.BAD_REQUEST
        val timestamp = LocalDateTime.now()
        val message = "param ${ex.name} should be of type ${ex.requiredType?.name}"
        return ErrorResponseDto(status.name, timestamp, message)
    }

    fun toResponse(ex: EntityNotFoundException): ErrorResponseDto {
        val status = ex.status
        val timestamp = LocalDateTime.now()
        val message = ex.message ?: ex.localizedMessage
        return ErrorResponseDto(status.name, timestamp, message)
    }

    fun toResponse(ex: HttpMessageNotReadableException): ErrorResponseDto {
        val status = HttpStatus.BAD_REQUEST
        val timestamp = LocalDateTime.now()
        val message = ex.message ?: ex.localizedMessage
        return ErrorResponseDto(status.name, timestamp, message)
    }

    fun toResponse(ex: UnauthorizedResourceException): ErrorResponseDto {
        val status = HttpStatus.FORBIDDEN
        val timestamp = LocalDateTime.now()
        val message = ex.message ?: ex.localizedMessage
        return ErrorResponseDto(status.name, timestamp, message)
    }

}