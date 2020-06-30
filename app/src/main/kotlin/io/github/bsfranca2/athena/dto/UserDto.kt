package io.github.bsfranca2.athena.dto

data class UserDto(
        val id: Int,
        val email: String,
        val password: String,
        val active: Boolean,
        val roles: List<String>
)