package io.github.bsfranca2.athena.dto

data class LoginResponseDto(val success: Boolean, val token: String, val msg: String = "")