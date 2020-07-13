package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.project.ProjectItemDto
import io.github.bsfranca2.athena.dto.scrum.ProductBacklogDto
import io.github.bsfranca2.athena.dto.scrum.ProductBacklogItemDto
import io.github.bsfranca2.athena.dto.scrum.ScrumBoardDto
import io.github.bsfranca2.athena.dto.scrum.SprintDto
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.scrum.ProductBacklog
import io.github.bsfranca2.athena.entity.scrum.ProductBacklogItem
import io.github.bsfranca2.athena.entity.scrum.ScrumBoard
import io.github.bsfranca2.athena.entity.scrum.Sprint

object ProjectItemAdapter {

    fun toDto(projectItem: ProjectItem): ProjectItemDto {
        val id = projectItem.id
        val name = projectItem.name
        val project = projectItem.project.id
        val type = projectItem.getType()
        val createdBy = projectItem.createdBy.id.toLong()
        val createdAt = projectItem.createdAt
        return ProjectItemDto(id, name, project, type, createdBy, createdAt)
    }

    fun toDto(scrumBoard: ScrumBoard): ScrumBoardDto {
        val sprints = scrumBoard.sprints.map { toDto(it) }.toMutableList()
        return ScrumBoardDto(scrumBoard.id, scrumBoard.name, scrumBoard.project.id, scrumBoard.getType(), scrumBoard.sprintActiveId, sprints, scrumBoard.createdBy.id.toLong(), scrumBoard.createdAt)
    }

    private fun toDto(item: ProductBacklogItem): ProductBacklogItemDto {
        return ProductBacklogItemDto(item.id, item.productBacklog.id, IssueAdapter.toDto(item.issue))
    }

    private fun toDto(sprint: Sprint): SprintDto {
        val (id, board, name, startDate, endDate, startedAt, endedAt, createdBy) = sprint
        val boardId = board.id
        val active = sprint.isActive()
        val createdByUserId = createdBy.id.toLong()
        return SprintDto(id, boardId, name, active, startDate, endDate, startedAt, endedAt, createdByUserId)
    }

}