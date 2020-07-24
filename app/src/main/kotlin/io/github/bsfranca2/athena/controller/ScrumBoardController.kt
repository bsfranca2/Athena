package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.scrum.RequestScrumBoardDto
import io.github.bsfranca2.athena.dto.scrum.RequestSprintBacklogItemDto
import io.github.bsfranca2.athena.dto.scrum.RequestSprintDto
import io.github.bsfranca2.athena.service.ScrumBoardService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/scrum-boards")
class ScrumBoardController(val scrumBoardService: ScrumBoardService) {

    @GetMapping("/{id}")
    fun getScrumBoard(@PathVariable id: Long)
            = scrumBoardService.getScumBoard(id)

    @PutMapping("/{id}")
    fun updateScrumBoard(@PathVariable id: Long, @RequestBody scrumBoardRequest: RequestScrumBoardDto)
            = scrumBoardService.updateScrumBoard(id, scrumBoardRequest)

    @DeleteMapping("/{id}")
    fun deleteScrumBoard(@PathVariable id: Long)
            = scrumBoardService.deleteScrumBoard(id)

    @PostMapping("/{scrumBoardId}/sprints") @ResponseStatus(HttpStatus.CREATED)
    fun createSprint(@PathVariable scrumBoardId: Long, @RequestBody requestDto: RequestSprintDto)
            = scrumBoardService.createSprint(scrumBoardId, requestDto)

    @PostMapping("/{scrumBoardId}/sprints/{sprintId}/start")
    fun startSprint(@PathVariable scrumBoardId: Long, @PathVariable sprintId: Long)
            = scrumBoardService.startSprint(scrumBoardId, sprintId)

    @PostMapping("/{scrumBoardId}/sprints/{sprintId}/end")
    fun endSprint(@PathVariable scrumBoardId: Long, @PathVariable sprintId: Long)
            = scrumBoardService.endSprint(scrumBoardId, sprintId)

    @PostMapping("/{scrumBoardId}/sprints/{sprintId}/backlog")
    fun addItemToSprintBacklog(@PathVariable scrumBoardId: Long, @PathVariable sprintId: Long, @Valid @RequestBody requestSprintBacklogItemDto: RequestSprintBacklogItemDto)
            = scrumBoardService.addItemToSprintBacklog(scrumBoardId, sprintId, requestSprintBacklogItemDto)

    @GetMapping("/{scrumBoardId}/sprints/{sprintId}/backlog")
    fun getSprintBacklog(@PathVariable scrumBoardId: Long, @PathVariable sprintId: Long)
            = scrumBoardService.getSprintBacklog(scrumBoardId, sprintId)

}
