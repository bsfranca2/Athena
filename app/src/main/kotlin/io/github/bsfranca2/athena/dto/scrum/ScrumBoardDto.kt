package io.github.bsfranca2.athena.dto.scrum

import io.github.bsfranca2.athena.dto.project.ProjectItemDto
import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime

class ScrumBoardDto(
        id: Long,
        name: String,
        projectId: Long,
        type: ProjectItemType,
        var sprintActiveIds: MutableSet<Long>,
        var sprints: MutableList<SprintDto>,
        createdByUserId: Long,
        createdAt: LocalDateTime
): ProjectItemDto(id, name, projectId, type, createdByUserId, createdAt)