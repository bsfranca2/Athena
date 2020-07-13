package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.scrum.ScrumBoard
import org.springframework.data.repository.CrudRepository

interface ProjectItemRepository : CrudRepository<ProjectItem, Long> {
    fun findByProject(project: Project): MutableList<ProjectItem>
}