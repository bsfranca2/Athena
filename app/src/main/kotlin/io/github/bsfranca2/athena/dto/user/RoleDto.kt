package io.github.bsfranca2.athena.dto.user

data class RoleDto(
        val name: String,
        val privileges: MutableList<String>
)