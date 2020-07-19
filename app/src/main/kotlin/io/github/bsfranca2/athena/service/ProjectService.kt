package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.ProjectAdapter
import io.github.bsfranca2.athena.adapter.ProjectItemAdapter
import io.github.bsfranca2.athena.dto.project.ProjectDto
import io.github.bsfranca2.athena.dto.project.ProjectItemDto
import io.github.bsfranca2.athena.dto.project.RequestProjectDto
import io.github.bsfranca2.athena.dto.project.RequestProjectItemDto
import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.ProjectMember
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.entity.scrum.ProductBacklog
import io.github.bsfranca2.athena.entity.scrum.ProductBacklogItem
import io.github.bsfranca2.athena.entity.scrum.ScrumBoard
import io.github.bsfranca2.athena.enum.ProjectItemType
import io.github.bsfranca2.athena.exception.ProjectNotFoundException
import io.github.bsfranca2.athena.exception.UnauthorizedResourceException
import io.github.bsfranca2.athena.exception.WorkspaceNotFoundException
import io.github.bsfranca2.athena.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProjectService(
        private val accountService: AccountService,
        private val projectRepository: ProjectRepository,
        private val projectItemRepository: ProjectItemRepository,
        private val projectMemberRepository: ProjectMemberRepository,
        private val workspaceRepository: WorkspaceRepository,
        private val roleRepository: RoleRepository
) {
    @Transactional
    fun createProject(workspaceId: Long, requestProjectDto: RequestProjectDto): ProjectDto {
        val workspace = workspaceRepository.findByIdOrNull(workspaceId)
                ?: throw WorkspaceNotFoundException(workspaceId)
        val loggedUser = accountService.loggedUser
        if (!workspace.isManager(loggedUser))
            throw UnauthorizedResourceException()
        val role = roleRepository.findByName("PROJECT_MANAGER") ?: throw Throwable("Role project manager not found")
        val project = Project(-1L, workspace, requestProjectDto.name, loggedUser, LocalDateTime.now())
        project.members.add(ProjectMember(-1, workspace, project, loggedUser, role))
        val projectSaved = projectRepository.save(project)
        return ProjectAdapter.toDto(projectSaved)
    }

    @Transactional
    fun listProjects(workspaceId: Long): List<ProjectDto> {
        val workspace = workspaceRepository.findByIdOrNull(workspaceId)
                ?: throw WorkspaceNotFoundException(workspaceId)
        val projectMembers = projectMemberRepository.findByWorkspaceAndUser(workspace, accountService.loggedUser)
        val projectsId = projectMembers.map { it.project.id }
        val projects = projectRepository.findAllById(projectsId)
        return projects.map { ProjectAdapter.toDto(it)}
    }

    @Transactional
    fun updateProject(workspaceId: Long, id: Long, requestProjectDto: RequestProjectDto): ProjectDto {
        workspaceRepository.findByIdOrNull(workspaceId)
                ?: throw WorkspaceNotFoundException(workspaceId)
        val project = projectRepository.findByIdOrNull(id)
                ?: throw ProjectNotFoundException(id)
        val member = projectMemberRepository.findByProjectAndUser(project, accountService.loggedUser)
                ?: throw UnauthorizedResourceException()
        if (!member.isProjectManager())
            throw UnauthorizedResourceException()
        project.name = requestProjectDto.name
        val projectSaved = projectRepository.save(project)
        return ProjectAdapter.toDto(projectSaved)
    }

    @Transactional
    fun deleteProject(id: Long) {
        TODO("not implemented yet")
    }

    @Transactional
    fun createProjectItem(projectId: Long, requestProjectItemDto: RequestProjectItemDto): ProjectItemDto {
        val project = projectRepository.findByIdOrNull(projectId)
                ?: throw ProjectNotFoundException(projectId)
        val member = projectMemberRepository.findByProjectAndUser(project, accountService.loggedUser)
                ?: throw UnauthorizedResourceException()
        if (!member.isProjectManager())
            throw UnauthorizedResourceException()
        val projectItem = projectItem(requestProjectItemDto, project)
        val projectItemSaved = projectItemRepository.save(projectItem)
        return ProjectItemAdapter.toDto(projectItemSaved)
    }

    private fun projectItem(requestProjectItemDto: RequestProjectItemDto, project: Project): ProjectItem {
        val id = -1L
        val (name, type) = requestProjectItemDto
        val productBacklogItems = mutableListOf<ProductBacklogItem>()
        val createdBy = accountService.loggedUser
        val createdAt = LocalDateTime.now()
        return when(type) {
            ProjectItemType.SCRUM_BOARD -> ScrumBoard(id, name, project, createdBy, createdAt)
            ProjectItemType.PRODUCT_BACKLOG -> ProductBacklog(id, name, project, productBacklogItems, createdBy, createdAt)
            ProjectItemType.DEFAULT -> ProjectItem(id, name, project, createdBy, createdAt)
        }
    }

    fun listProjectItems(projectId: Long): List<ProjectItemDto> {
        val project = projectRepository.findByIdOrNull(projectId)
                ?: throw ProjectNotFoundException(projectId)
        projectMemberRepository.findByProjectAndUser(project, accountService.loggedUser)
                ?: throw UnauthorizedResourceException()
        val projectItems = projectItemRepository.findByProject(project)
        return projectItems.map { ProjectItemAdapter.toDto(it) }
    }

}