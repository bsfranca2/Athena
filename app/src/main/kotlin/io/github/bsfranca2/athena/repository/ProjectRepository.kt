package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.Workspace
import org.springframework.data.repository.CrudRepository

interface ProjectRepository: CrudRepository<Project, Long> {
    fun findByWorkspace(workspace: Workspace): MutableList<Project>
}