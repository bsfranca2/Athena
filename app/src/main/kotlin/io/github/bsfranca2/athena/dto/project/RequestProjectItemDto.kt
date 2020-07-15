package io.github.bsfranca2.athena.dto.project

import io.github.bsfranca2.athena.enum.ProjectItemType
import javax.validation.constraints.NotBlank

data class RequestProjectItemDto(
        @field:NotBlank
        val name: String = "",
        val type: ProjectItemType = ProjectItemType.DEFAULT
)