package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.WorkspaceAdapter
import io.github.bsfranca2.athena.dto.workspace.RequestWorkspaceDto
import io.github.bsfranca2.athena.dto.workspace.WorkspaceDto
import io.github.bsfranca2.athena.entity.*
import io.github.bsfranca2.athena.exception.UnauthorizedResourceException
import io.github.bsfranca2.athena.exception.workspace.WorkspaceNotFoundException
import io.github.bsfranca2.athena.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WorkspaceService(
        val workspaceRepository: WorkspaceRepository,
        val projectMemberRepository: ProjectMemberRepository,
        val accountService: AccountService
) {

    fun createWorkspace(requestWorkspaceDto: RequestWorkspaceDto): WorkspaceDto {
        val (name, slug) = requestWorkspaceDto
        val workspace = createUserWorkspace(name, slug, accountService.loggedUser)
        return WorkspaceAdapter.toDto(workspace)
    }

    @Transactional
    fun createUserWorkspace(name: String, slug: String, manager: User): Workspace {
        val workspace = Workspace(-1, name, slug)
        workspace.managers.add(manager)
        return workspaceRepository.save(workspace)
    }

    fun getWorkspace(id: Long): WorkspaceDto {
        val workspace = workspaceRepository.findByIdOrNull(id)
                ?: throw WorkspaceNotFoundException(id)
        val user = accountService.loggedUser
        val isWorkspaceManager = workspace.isManager(user)
        if (!isWorkspaceManager) {
            val isMemberOfSomeProject = projectMemberRepository.existsProjectMemberByWorkspaceAndUser(workspace, user)
            if (!isMemberOfSomeProject)
                throw UnauthorizedResourceException()
        }
        return WorkspaceAdapter.toDto(workspace)
    }

    @Transactional
    fun updateWorkspace(id: Long, requestWorkspaceDto: RequestWorkspaceDto): WorkspaceDto {
        val workspace = workspaceRepository.findByIdOrNull(id)
                ?: throw WorkspaceNotFoundException(id)
        if (!workspace.isManager(accountService.loggedUser))
            throw UnauthorizedResourceException()
        workspace.name = requestWorkspaceDto.name
        workspace.slug = requestWorkspaceDto.slug
        val workspaceSaved = workspaceRepository.save(workspace)
        return WorkspaceAdapter.toDto(workspaceSaved)
    }

}