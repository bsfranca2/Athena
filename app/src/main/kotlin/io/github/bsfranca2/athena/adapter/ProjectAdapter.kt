package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.project.ProjectDto
import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem

object ProjectAdapter {

    fun toDto(project: Project): ProjectDto {
        val (id, name, createdBy, createdAt) = project
        val createdById = createdBy.id
        val items = project.items.map { toDto(it) }.toMutableList()
        return ProjectDto(id, name, items, createdById, createdAt)
    }

    private fun toDto(projectItem: ProjectItem): ProjectDto.Item {
        val id = projectItem.id
        val name = projectItem.name
        val project = projectItem.project.id
        val type = projectItem.getType()
        val createdBy = projectItem.createdBy.id
        val createdAt = projectItem.createdAt
        return ProjectDto.Item(id, name, project, type, createdBy, createdAt)
    }

}
