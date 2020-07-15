package io.github.bsfranca2.athena.dto.auth

data class LoginResponseDto(
        val success: Boolean,
        val token: String,
        val msg: String = ""
)