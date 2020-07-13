package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.scrum.RequestScrumBoardDto
import io.github.bsfranca2.athena.dto.scrum.RequestSprintDto
import io.github.bsfranca2.athena.service.ScrumBoardService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/{scrumBoardId}/sprints/{id}/start")
    fun startSprint(@PathVariable scrumBoardId: Long, @PathVariable id: Long)
            = scrumBoardService.startSprint(scrumBoardId, id)

    @PostMapping("/{scrumBoardId}/sprints/{id}/end")
    fun endSprint(@PathVariable scrumBoardId: Long, @PathVariable id: Long)
            = scrumBoardService.endSprint(scrumBoardId, id)

}
