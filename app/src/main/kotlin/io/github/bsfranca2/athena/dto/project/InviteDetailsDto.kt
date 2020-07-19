package io.github.bsfranca2.athena.dto.project

data class InviteDetailsDto(
        val projectName: String,
        val hasAccount: Boolean,
        val email: String,
        val token: String
)