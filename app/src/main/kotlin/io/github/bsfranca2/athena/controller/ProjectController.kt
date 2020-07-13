package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.project.RequestProjectDto
import io.github.bsfranca2.athena.dto.project.RequestProjectItemDto
import io.github.bsfranca2.athena.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(val projectService: ProjectService) {

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody requestProjectDto: RequestProjectDto)
            = projectService.createProject(requestProjectDto)

    @GetMapping
    fun listProjects()
            = projectService.listProjects()

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody requestProjectDto: RequestProjectDto)
            = projectService.updateProject(id, requestProjectDto)

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long)
            = projectService.deleteProject(id)

    @PostMapping("/{projectId}/items") @ResponseStatus(HttpStatus.CREATED)
    fun createProjectItem(@PathVariable projectId: Long, @RequestBody requestProjectItemDto: RequestProjectItemDto)
            = projectService.createProjectItem(projectId, requestProjectItemDto)

    @GetMapping("/{projectId}/items")
    fun listProjectItems(@PathVariable projectId: Long)
            = projectService.listProjectItems(projectId)

}