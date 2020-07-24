package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.scrum.SprintBacklogDto
import io.github.bsfranca2.athena.entity.scrum.SprintBacklog

object SprintBacklogAdapter {

    fun toDto(sprintBacklog: SprintBacklog): SprintBacklogDto {
        val items = sprintBacklog.items.map { ProjectItemAdapter.toDto(it) }
        return SprintBacklogDto(sprintBacklog.id, sprintBacklog.sprint.id, items)
    }

}