package io.github.bsfranca2.athena.dto.response

import java.time.LocalDateTime

data class ErrorResponseDto(
        val status: String = "",
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val message: String = "",
        val errors: Any? = null
)