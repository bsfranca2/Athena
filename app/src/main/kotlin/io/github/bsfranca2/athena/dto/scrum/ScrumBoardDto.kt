package io.github.bsfranca2.athena.dto.scrum

import io.github.bsfranca2.athena.dto.project.ProjectItemDto
import io.github.bsfranca2.athena.enum.ProjectItemType
import java.time.LocalDateTime

class ScrumBoardDto(
        id: Long,
        name: String,
        project: Long,
        type: ProjectItemType,
        var sprintActiveId: Long?,
        var sprints: MutableList<SprintDto>,
        createdBy: Long,
        createdAt: LocalDateTime
): ProjectItemDto(id, name, project, type, createdBy, createdAt)