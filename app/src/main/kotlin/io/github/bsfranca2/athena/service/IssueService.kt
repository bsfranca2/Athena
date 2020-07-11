package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.IssueAdapter
import io.github.bsfranca2.athena.adapter.TimeEntryAdapter
import io.github.bsfranca2.athena.dto.IssueDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.TimeEntry
import io.github.bsfranca2.athena.exception.IssueNotFoundException
import io.github.bsfranca2.athena.exception.TimeEntryNotFoundException
import io.github.bsfranca2.athena.repository.IssueRepository
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class IssueService(val userService: UserService, val issueRepository: IssueRepository, val userRepository: UserRepository) {

    fun createIssue(issueDto: IssueDto): IssueDto {
        val user = userService.loggedUser
        val (_, title, description, status, priority, assignedToUsersId, startDate, endDate, estimatedTime) = issueDto
        val assignedTo = userRepository.findAllById(assignedToUsersId).toMutableList()
        val timeEntries = mutableListOf<TimeEntry>()
        val issue = Issue(-1, title, description, status, priority, assignedTo, startDate, endDate, estimatedTime, timeEntries, user)
        val issueSaved = issueRepository.save(issue)
        return IssueAdapter.toDto(issueSaved)
    }

    fun listIssues(): List<IssueDto> {
        val user = userService.loggedUser
        val issues = issueRepository.findByCreatedBy(user)
        return issues.map { IssueAdapter.toDto(it) }
    }

    fun updateIssue(id: Int, issueUpdateDto: IssueDto): IssueDto {
        val issue = issueRepository.findByIdOrNull(id) ?: throw IssueNotFoundException(id)
        issue.title = issueUpdateDto.title
        issue.description = issueUpdateDto.description
        issue.status = issueUpdateDto.status
        issue.startDate = issueUpdateDto.startDate
        issue.endDate = issueUpdateDto.endDate
        issue.estimatedTime = issueUpdateDto.estimatedTime
        val assignedToUsers = userRepository.findAllById(issueUpdateDto.assignedTo)
        issue.assignedTo.clear()
        issue.assignedTo.addAll(assignedToUsers)
        val issueSaved = issueRepository.save(issue)
        return IssueAdapter.toDto(issueSaved)
    }

    fun deleteIssue(id: Int) {
        val issue  = issueRepository.findByIdOrNull(id) ?: throw IssueNotFoundException(id)
        return issueRepository.delete(issue)
    }

    fun addTimeEntry(issueId: Int, timeEntryDto: TimeEntryDto): List<TimeEntryDto> {
        val createdBy = userService.loggedUser
        val (_, _, description, registerAt, startAt, endAt, _, createdAt) = timeEntryDto
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val timeEntry = TimeEntry(-1, issue, description, registerAt, startAt, endAt, createdBy, createdAt)
        issue.timeEntries.add(timeEntry)
        val issueSaved = issueRepository.save(issue)
        return issueSaved.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun listTimeEntries(issueId: Int): List<TimeEntryDto> {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        return issue.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun updateTimeEntry(issueId: Int, id: Int, timeEntryDto: TimeEntryDto): List<TimeEntryDto> {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val timeEntry = issue.timeEntries.find { it.id == id } ?: throw TimeEntryNotFoundException(id)
        val (_, _, newDescription, newRegisterAt, newStartAt, newEndAt) = timeEntryDto
        val timeEntryUpdated = timeEntry.copy()
        timeEntryUpdated.description = newDescription
        timeEntryUpdated.registerAt = newRegisterAt
        timeEntryUpdated.startAt = newStartAt
        timeEntryUpdated.endAt = newEndAt
        timeEntryUpdated.updatedAt = LocalDateTime.now()
        issue.timeEntries.remove(timeEntry)
        issue.timeEntries.add(timeEntryUpdated)
        val issueSaved = issueRepository.save(issue)
        return issueSaved.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun removeTimeEntry(issueId: Int, id: Int) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val timeEntry = issue.timeEntries.find { it.id == id } ?: throw TimeEntryNotFoundException(id)
        issue.timeEntries.remove(timeEntry)
        issueRepository.save(issue)
    }

}