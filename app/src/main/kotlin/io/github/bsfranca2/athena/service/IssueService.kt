package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.IssueAdapter
import io.github.bsfranca2.athena.adapter.TimeEntryAdapter
import io.github.bsfranca2.athena.dto.issue.IssueDto
import io.github.bsfranca2.athena.dto.issue.TimeEntryDto
import io.github.bsfranca2.athena.dto.issue.RequestIssueDto
import io.github.bsfranca2.athena.dto.issue.RequestTimeEntryDto
import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.TimeEntry
import io.github.bsfranca2.athena.enum.IssueType
import io.github.bsfranca2.athena.exception.issue.IssueNotFoundException
import io.github.bsfranca2.athena.exception.issue.TimeEntryNotFoundException
import io.github.bsfranca2.athena.repository.IssueRepository
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class IssueService(val userService: UserService, val issueRepository: IssueRepository, val userRepository: UserRepository) {

    @Transactional
    fun createIssue(issueDto: RequestIssueDto): IssueDto {
        val issue = createIssueByDto(issueDto)
        val issueSaved = issueRepository.save(issue)
        return IssueAdapter.toDto(issueSaved)
    }

    @Transactional
    fun createIssueByDto(requestIssueDto: RequestIssueDto): Issue {
        val user = userService.loggedUser
        val (issueType, title, description, status, priority, startDate, endDate, estimatedTime, storyPoints, parentId) = requestIssueDto
        var parent: Issue? = null
        parentId?.let { parent = issueRepository.findByIdOrNull(it)?: throw IssueNotFoundException(it) }
        val issue = Issue(-1, issueType, title, description, status, priority, startDate, endDate, estimatedTime, storyPoints, parent, user)
        if (issue.canAssign()) {
            val users = userRepository.findAllById(requestIssueDto.assignedToUsersId)
            issue.assignToMultiples(users.toList())
        }
        return issue
    }

    fun listIssues(): List<IssueDto> {
        val user = userService.loggedUser
        val issues = issueRepository.findByCreatedByAndParentIsNull(user)
        return issues.map { IssueAdapter.toDto(it) }
    }

    @Transactional
    fun updateIssue(id: Long, issueUpdateDto: RequestIssueDto): IssueDto {
        val issue = issueRepository.findByIdOrNull(id) ?: throw IssueNotFoundException(id)
        issue.apply {
            type = issueUpdateDto.issueType
            title = issueUpdateDto.title
            description = issueUpdateDto.description
            status = issueUpdateDto.status
            startDate = issueUpdateDto.startDate
            endDate = issueUpdateDto.endDate
            estimatedTime = issueUpdateDto.estimatedTime
            storyPoints = issueUpdateDto.storyPoints
        }
        if (issueUpdateDto.parentId != issue.parent?.id) {
            issue.parent = if (issueUpdateDto.parentId == null) null else issueRepository.findByIdOrNull(issueUpdateDto.parentId)
        }
        val assignedToUsers = if (issue.canAssign()) userRepository.findAllById(issueUpdateDto.assignedToUsersId) else mutableListOf()
        issue.assignedTo.clear()
        issue.assignedTo.addAll(assignedToUsers)
        val issueSaved = issueRepository.save(issue)
        return IssueAdapter.toDto(issueSaved)
    }

    @Transactional
    fun deleteIssue(id: Long) {
        val issue  = issueRepository.findByIdOrNull(id) ?: throw IssueNotFoundException(id)
        return issueRepository.delete(issue)
    }

    @Transactional
    fun addTimeEntry(issueId: Long, requestTimeEntryDto: RequestTimeEntryDto): List<TimeEntryDto> {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val (description, registerAt, timeSpent) = requestTimeEntryDto
        val createdBy = userService.loggedUser
        val createdAt = LocalDateTime.now()
        val timeEntry = TimeEntry(-1, issue, description, registerAt, timeSpent, createdBy, createdAt)
        issue.timeEntries.add(timeEntry)
        val issueSaved = issueRepository.save(issue)
        return issueSaved.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun listTimeEntries(issueId: Long): List<TimeEntryDto> {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        return issue.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    @Transactional
    fun updateTimeEntry(issueId: Long, id: Long, requestTimeEntryDto: RequestTimeEntryDto): List<TimeEntryDto> {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val timeEntry = issue.timeEntries.find { it.id == id } ?: throw TimeEntryNotFoundException(id)
        val (newDescription, newRegisterAt, newTimeSpent) = requestTimeEntryDto
        val timeEntryUpdated = timeEntry.copy().apply {
            description = newDescription
            registerAt = newRegisterAt
            timeSpent = newTimeSpent
            updatedAt = LocalDateTime.now()
        }
        issue.timeEntries.remove(timeEntry)
        issue.timeEntries.add(timeEntryUpdated)
        val issueSaved = issueRepository.save(issue)
        return issueSaved.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    @Transactional
    fun removeTimeEntry(issueId: Long, id: Long) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val timeEntry = issue.timeEntries.find { it.id == id } ?: throw TimeEntryNotFoundException(id)
        issue.timeEntries.remove(timeEntry)
        issueRepository.save(issue)
    }

}