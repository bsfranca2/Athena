package io.github.bsfranca2.athena.dto.auth

import javax.validation.constraints.*

data class RequestAccountDto(
        @field:NotBlank
        @field:Email
        val email: String = "",
        @field:NotBlank
        @field:Size(min = 8)
        val password: String = "",
        val workspaceName: String? = null,
        val workspaceSlug: String? = null
)