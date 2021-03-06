package io.github.bsfranca2.athena.dto.project

import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime

data class ProjectDto(
        val id: Long,
        val workspaceId: Long,
        val name: String,
        val items: MutableList<Item>,
        val createdByUserId: Long,
        val createdAt: LocalDateTime
) {
    data class Item(
            val id: Long,
            val name: String,
            val projectId: Long,
            val type: ProjectItemType,
            val createdByUserId: Long,
            val createdAt: LocalDateTime
    )
}
