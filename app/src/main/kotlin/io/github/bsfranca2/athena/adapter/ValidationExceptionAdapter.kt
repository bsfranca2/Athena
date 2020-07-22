package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.response.ErrorResponseDto
import io.github.bsfranca2.athena.dto.validation.ValidationDto
import io.github.bsfranca2.athena.enum.ValidationType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime

object ValidationExceptionAdapter {

    fun toResponse(ex: MethodArgumentNotValidException): ErrorResponseDto {
        val errors = mutableListOf<ValidationDto>()
        ex.bindingResult.fieldErrors.forEach {
            errors.add(ValidationDto(getError(it.codes), getPath(it.field)))
        }
        val status = HttpStatus.BAD_REQUEST
        val timestamp = LocalDateTime.now()
        val message = "Validation error"
        return ErrorResponseDto(status.name, timestamp, message, errors)
    }

    fun singleNotNullToResponse(field: String): ErrorResponseDto {
        val errors = mutableListOf<ValidationDto>()
        val validationType = ValidationType.NotNull
        val path = getPath(field)
        errors.add(ValidationDto(validationType.error, path))
        val status = HttpStatus.BAD_REQUEST
        val timestamp = LocalDateTime.now()
        val message = "Validation error"
        return ErrorResponseDto(status.name, timestamp, message, errors)
    }

    private fun getError(codes: Array<String>?): String {
        if (codes == null || codes.isEmpty()) return "Error undefined"
        val validation = ValidationType.valueOf(codes.last())
        return validation.error
    }

    private fun getPath(field: String): MutableList<Any> {
        val path = mutableListOf<Any>()
        val properties = field.split(".")
        properties.forEach { path.addAll(splitPropertyAndIndex(it)) }
        path.remove("")
        return path
    }

    private fun splitPropertyAndIndex(property: String): List<Any> {
        return property.split("[", "]").map { it.toIntOrNull() ?: it }
    }

}