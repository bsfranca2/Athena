package io.github.bsfranca2.athena.dto

import javax.validation.constraints.*

data class AuthenticationDto(
        @field:NotBlank
        @field:Email
        val email: String,
        @field:NotBlank
        @field:Size(min = 8)
        val password: String
)