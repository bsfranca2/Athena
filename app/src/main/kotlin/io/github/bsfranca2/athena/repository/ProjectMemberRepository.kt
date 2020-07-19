package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectMember
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.entity.Workspace
import org.springframework.data.repository.CrudRepository

interface ProjectMemberRepository : CrudRepository<ProjectMember, Long> {
    fun findByProjectAndUser(project: Project, user: User): ProjectMember?
    fun findByWorkspaceAndUser(workspace: Workspace, user: User): MutableList<ProjectMember>
    fun existsProjectMemberByWorkspaceAndUser(workspace: Workspace, user: User): Boolean
}