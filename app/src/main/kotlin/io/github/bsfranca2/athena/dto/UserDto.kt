package io.github.bsfranca2.athena.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserDto(
        val email: String,
        val password: String,
        val active: Boolean,
        val roles: MutableList<RoleDto>
)