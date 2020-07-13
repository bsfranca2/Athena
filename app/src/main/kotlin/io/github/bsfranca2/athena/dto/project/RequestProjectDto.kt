package io.github.bsfranca2.athena.dto.project

import javax.validation.constraints.NotBlank

data class RequestProjectDto(
        @field:NotBlank
        val name: String
)