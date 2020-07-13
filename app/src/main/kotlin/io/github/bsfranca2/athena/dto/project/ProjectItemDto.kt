package io.github.bsfranca2.athena.dto.project

import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime

open class ProjectItemDto(
        val id: Long,
        val name: String,
        val project: Long,
        val type: ProjectItemType,
        val createdBy: Long,
        val createdAt: LocalDateTime
)