package io.github.bsfranca2.athena.dto

data class UserDto(
        val email: String,
        val password: String,
        val active: Boolean,
        val roles: MutableMap<String, List<String>>
)