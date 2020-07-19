package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.workspace.RequestWorkspaceDto
import io.github.bsfranca2.athena.service.WorkspaceService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/workspaces")
class WorkspaceController(val workspaceService: WorkspaceService) {

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun createWorkspace(@Valid @RequestBody requestWorkspaceDto: RequestWorkspaceDto)
            = workspaceService.createWorkspace(requestWorkspaceDto)

    @GetMapping("/{id}")
    fun getWorkspace(@PathVariable id: Long)
            = workspaceService.getWorkspace(id)

    @PutMapping("/{id}")
    fun updateWorkspace(@PathVariable id: Long, @Valid @RequestBody requestWorkspaceDto: RequestWorkspaceDto)
            = workspaceService.updateWorkspace(id, requestWorkspaceDto)

}