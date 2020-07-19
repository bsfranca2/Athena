package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.project.RequestMemberInviteDto
import io.github.bsfranca2.athena.dto.project.RequestProjectDto
import io.github.bsfranca2.athena.dto.project.RequestProjectItemDto
import io.github.bsfranca2.athena.service.InviteService
import io.github.bsfranca2.athena.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/workspaces/{workspaceId}/projects")
class ProjectController(val projectService: ProjectService, val inviteService: InviteService) {

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@PathVariable workspaceId: Long, @RequestBody requestProjectDto: RequestProjectDto)
            = projectService.createProject(workspaceId, requestProjectDto)

    @GetMapping
    fun listProjects(@PathVariable workspaceId: Long)
            = projectService.listProjects(workspaceId)

    @PutMapping("/{id}")
    fun updateProject(@PathVariable workspaceId: Long, @PathVariable id: Long, @RequestBody requestProjectDto: RequestProjectDto)
            = projectService.updateProject(workspaceId, id, requestProjectDto)

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable workspaceId: Long, @PathVariable id: Long)
            = projectService.deleteProject(id)

    @PostMapping("/{projectId}/items") @ResponseStatus(HttpStatus.CREATED)
    fun createProjectItem(@PathVariable projectId: Long, @RequestBody requestProjectItemDto: RequestProjectItemDto)
            = projectService.createProjectItem(projectId, requestProjectItemDto)

    @GetMapping("/{projectId}/items")
    fun listProjectItems(@PathVariable projectId: Long)
            = projectService.listProjectItems(projectId)

    @PostMapping("/{projectId}/invitations")
    fun inviteMember(@PathVariable projectId: Long, @RequestBody invitationsDto: List<RequestMemberInviteDto>)
            = inviteService.inviteMember(projectId, invitationsDto)

}