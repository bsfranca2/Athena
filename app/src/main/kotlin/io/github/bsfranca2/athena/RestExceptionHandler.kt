package io.github.bsfranca2.athena

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.github.bsfranca2.athena.adapter.ExceptionAdapter
import io.github.bsfranca2.athena.adapter.ValidationExceptionAdapter
import io.github.bsfranca2.athena.dto.response.ErrorResponseDto
import io.github.bsfranca2.athena.exception.EntityNotFoundException
import io.github.bsfranca2.athena.exception.UnauthorizedResourceException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(ex: EntityNotFoundException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity(ExceptionAdapter.toResponse(ex), HttpStatus.BAD_REQUEST)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(ValidationExceptionAdapter.toResponse(ex), HttpStatus.BAD_REQUEST)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val cause = ex.cause
        if (cause is MissingKotlinParameterException) {
            val parameter = getParameterNull(cause)
            val errorResponse = ValidationExceptionAdapter.singleNotNullToResponse(parameter)
            return ResponseEntity(errorResponse, status)
        }
        return ResponseEntity(ExceptionAdapter.toResponse(ex), status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(ex: MethodArgumentTypeMismatchException): ResponseEntity<Any> {
        return ResponseEntity(ExceptionAdapter.toResponse(ex), HttpStatus.BAD_REQUEST)
    }

    private fun getParameterNull(cause: MissingKotlinParameterException): String {
        val name = cause.path.fold("") { jsonPath, ref ->
            val suffix = when {
                ref.index > -1 -> "[${ref.index}]"
                else -> ".${ref.fieldName}"
            }
            (jsonPath + suffix).removePrefix(".")
        }
        return name
    }

    @ExceptionHandler(UnauthorizedResourceException::class)
    fun handleUnauthorizedResource(ex: UnauthorizedResourceException): ResponseEntity<Any> {
        return ResponseEntity(ExceptionAdapter.toResponse(ex), HttpStatus.FORBIDDEN)
    }

}
