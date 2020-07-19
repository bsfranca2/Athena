package io.github.bsfranca2.athena.dto.user

data class UserDto(
        val id: Long,
        val email: String,
        val active: Boolean,
        val roles: List<String>
)