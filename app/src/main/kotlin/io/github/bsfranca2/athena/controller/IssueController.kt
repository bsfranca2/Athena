package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.issue.IssueDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.dto.issue.RequestIssueDto
import io.github.bsfranca2.athena.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/issues")
class IssueController(val issueService: IssueService) {

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun createIssue(@Valid @RequestBody issueDto: RequestIssueDto)
            = issueService.createIssue(issueDto)

    @GetMapping
    fun listIssues()
            = issueService.listIssues()

    @PutMapping("/{id}")
    fun updateIssue(@PathVariable id: Int, @Valid @RequestBody issueUpdateDto: RequestIssueDto)
            = issueService.updateIssue(id, issueUpdateDto)

    @DeleteMapping("/{id}")
    fun deleteIssue(@PathVariable id: Int)
            = issueService.deleteIssue(id)

    @PostMapping("/{issueId}/time-entries") @ResponseStatus(HttpStatus.CREATED)
    fun addTimeEntry(@PathVariable issueId: Int, @RequestBody timeEntryDto: TimeEntryDto)
            = issueService.addTimeEntry(issueId, timeEntryDto)

    @GetMapping("/{issueId}/time-entries")
    fun listTimeEntries(@PathVariable issueId: Int)
            = issueService.listTimeEntries(issueId)

    @PutMapping("/{issueId}/time-entries/{id}")
    fun updateTimeEntry(@PathVariable issueId: Int, @PathVariable id: Int, @Valid @RequestBody timeEntryDto: TimeEntryDto)
            = issueService.updateTimeEntry(issueId, id, timeEntryDto)

    @DeleteMapping("/{issueId}/time-entries/{id}")
    fun removeTimeEntry(@PathVariable issueId: Int, @PathVariable id: Int)
            = issueService.removeTimeEntry(issueId, id)

}