package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.ProjectAdapter
import io.github.bsfranca2.athena.adapter.ProjectItemAdapter
import io.github.bsfranca2.athena.dto.project.ProjectDto
import io.github.bsfranca2.athena.dto.project.ProjectItemDto
import io.github.bsfranca2.athena.dto.project.RequestProjectDto
import io.github.bsfranca2.athena.dto.project.RequestProjectItemDto
import io.github.bsfranca2.athena.entity.Project
import io.github.bsfranca2.athena.entity.ProjectItem
import io.github.bsfranca2.athena.entity.scrum.ProductBacklog
import io.github.bsfranca2.athena.entity.scrum.ProductBacklogItem
import io.github.bsfranca2.athena.entity.scrum.ScrumBoard
import io.github.bsfranca2.athena.enum.ProjectItemType
import io.github.bsfranca2.athena.exception.ProjectNotFoundException
import io.github.bsfranca2.athena.repository.ProjectItemRepository
import io.github.bsfranca2.athena.repository.ProjectRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ProjectService(
        val userService: UserService,
        val projectRepository: ProjectRepository,
        val projectItemRepository: ProjectItemRepository
) {

    @Transactional
    fun createProject(requestProjectDto: RequestProjectDto): ProjectDto {
        val (name) = requestProjectDto
        val createdBy = userService.loggedUser
        val createdAt = LocalDateTime.now()
        val project = Project(-1L, name, createdBy, createdAt)
        val projectSaved = projectRepository.save(project)
        return ProjectAdapter.toDto(projectSaved)
    }

    fun listProjects(): List<ProjectDto> {
        val projects = projectRepository.findAll()
        return projects.map { ProjectAdapter.toDto(it)}
    }

    @Transactional
    fun updateProject(id: Long, requestProjectDto: RequestProjectDto): ProjectDto {
        val project = projectRepository.findByIdOrNull(id) ?: throw ProjectNotFoundException(id)
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
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ProjectNotFoundException(projectId)
        val id = -1L
        val (name, type) = requestProjectItemDto
        val productBacklogItems = mutableListOf<ProductBacklogItem>()
        val createdBy = userService.loggedUser
        val createdAt = LocalDateTime.now()
        val projectItem = when(type) {
            ProjectItemType.SCRUM_BOARD -> ScrumBoard(id, name, project, createdBy, createdAt)
            ProjectItemType.PRODUCT_BACKLOG -> ProductBacklog(id, name, project, productBacklogItems, createdBy, createdAt)
            ProjectItemType.DEFAULT -> ProjectItem(id, name, project, createdBy, createdAt)
        }
        val projectItemSaved = projectItemRepository.save(projectItem)
        return ProjectItemAdapter.toDto(projectItemSaved)
    }

    fun listProjectItems(projectId: Long): List<ProjectItemDto> {
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ProjectNotFoundException(projectId)
        val projectItems = projectItemRepository.findByProject(project)
        return projectItems.map { ProjectItemAdapter.toDto(it) }
    }

}