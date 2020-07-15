package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.issue.RequestIssueDto
import io.github.bsfranca2.athena.dto.issue.RequestTimeEntryDto
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
    fun updateIssue(@PathVariable id: Long, @Valid @RequestBody issueUpdateDto: RequestIssueDto)
            = issueService.updateIssue(id, issueUpdateDto)

    @DeleteMapping("/{id}")
    fun deleteIssue(@PathVariable id: Long)
            = issueService.deleteIssue(id)

    @PostMapping("/{issueId}/time-entries") @ResponseStatus(HttpStatus.CREATED)
    fun addTimeEntry(@PathVariable issueId: Long, @RequestBody requestTimeEntryDto: RequestTimeEntryDto)
            = issueService.addTimeEntry(issueId, requestTimeEntryDto)

    @GetMapping("/{issueId}/time-entries")
    fun listTimeEntries(@PathVariable issueId: Long)
            = issueService.listTimeEntries(issueId)

    @PutMapping("/{issueId}/time-entries/{id}")
    fun updateTimeEntry(@PathVariable issueId: Long, @PathVariable id: Long, @RequestBody requestTimeEntryDto: RequestTimeEntryDto)
            = issueService.updateTimeEntry(issueId, id, requestTimeEntryDto)

    @DeleteMapping("/{issueId}/time-entries/{id}")
    fun removeTimeEntry(@PathVariable issueId: Long, @PathVariable id: Long)
            = issueService.removeTimeEntry(issueId, id)

}