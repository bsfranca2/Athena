package io.github.bsfranca2.athena.dto.workspace

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RequestWorkspaceDto(
        @field:NotBlank
        val name: String = "",
        @field:NotBlank
        val slug: String = ""
)
