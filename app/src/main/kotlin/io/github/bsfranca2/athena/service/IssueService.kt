package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.IssueAdapter
import io.github.bsfranca2.athena.adapter.TimeEntryAdapter
import io.github.bsfranca2.athena.dto.issue.IssueDto
import io.github.bsfranca2.athena.dto.issue.TimeEntryDto
import io.github.bsfranca2.athena.dto.issue.RequestIssueDto
import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.TimeEntry
import io.github.bsfranca2.athena.enum.IssueType
import io.github.bsfranca2.athena.exception.IssueNotFoundException
import io.github.bsfranca2.athena.exception.TimeEntryNotFoundException
import io.github.bsfranca2.athena.repository.IssueRepository
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class IssueService(val userService: UserService, val issueRepository: IssueRepository, val userRepository: UserRepository) {

    fun createIssue(issueDto: RequestIssueDto): IssueDto {
        val user = userService.loggedUser
        val (issueType, title, description, status, priority, startDate, endDate, estimatedTime, storyPoints, parentId, assignedToUsersId) = issueDto
        val canAssign = issueType != IssueType.DEFAULT && issueType != IssueType.EPIC
        val assignedTo = if (canAssign) userRepository.findAllById(assignedToUsersId).toMutableList() else mutableListOf()
        val timeEntries = mutableListOf<TimeEntry>()
        var parent: Issue? = null
        parentId?.let {
            parent = issueRepository.findByIdOrNull(it) ?: throw IssueNotFoundException(it)
        }
        val issue = Issue(-1, issueType, title, description, status, priority, startDate, endDate, estimatedTime, storyPoints, timeEntries, assignedTo, parent, user)
        val issueSaved = issueRepository.save(issue)
        return IssueAdapter.toDto(issueSaved)
    }

    fun listIssues(): List<IssueDto> {
        val user = userService.loggedUser
        val issues = issueRepository.findByCreatedByAndParentIsNull(user)
        return issues.map { IssueAdapter.toDto(it) }
    }

    fun updateIssue(id: Int, issueUpdateDto: RequestIssueDto): IssueDto {
        val issue = issueRepository.findByIdOrNull(id) ?: throw IssueNotFoundException(id)
        issue.type = issueUpdateDto.issueType
        issue.title = issueUpdateDto.title
        issue.description = issueUpdateDto.description
        issue.status = issueUpdateDto.status
        issue.startDate = issueUpdateDto.startDate
        issue.endDate = issueUpdateDto.endDate
        issue.estimatedTime = issueUpdateDto.estimatedTime
        issue.storyPoints = issueUpdateDto.storyPoints
        if (issueUpdateDto.parent != issue.parent?.id) {
            issue.parent = if (issueUpdateDto.parent == null) null else issueRepository.findByIdOrNull(issueUpdateDto.parent)
        }
        val canAssign = issueUpdateDto.issueType != IssueType.DEFAULT && issueUpdateDto.issueType != IssueType.EPIC
        val assignedToUsers = if (canAssign) userRepository.findAllById(issueUpdateDto.assignedTo) else mutableListOf()
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
        val (_, _, description, registerAt, timeSpent, _, createdAt) = timeEntryDto
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw IssueNotFoundException(issueId)
        val timeEntry = TimeEntry(-1, issue, description, registerAt, timeSpent, createdBy, createdAt)
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
        val (_, _, newDescription, newRegisterAt, newTimeSpent) = timeEntryDto
        val timeEntryUpdated = timeEntry.copy()
        timeEntryUpdated.description = newDescription
        timeEntryUpdated.registerAt = newRegisterAt
        timeEntryUpdated.timeSpent = newTimeSpent
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