package io.github.bsfranca2.athena.dto

data class RoleDto(
        val name: String,
        val privileges: MutableList<String>
)