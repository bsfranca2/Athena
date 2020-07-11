package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.IssueDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.TimeEntry

object IssueAdapter {

    fun toDto(issue: Issue): IssueDto {
        val (id, title, description, status, priority, assignedTo, startDate, endDate, estimatedTime, _, createdBy) = issue
        val assignedToList = assignedTo.map { it.id }.toMutableList()
        return IssueDto(id, title, description, status, priority, assignedToList, startDate, endDate, estimatedTime, createdBy.id)
    }

}

object TimeEntryAdapter {
    fun toDto(timeEntry: TimeEntry): TimeEntryDto {
        val (id, issue, description, registerAt, timeSpent, createdBy, createdAt, updatedAt) = timeEntry
        val issueId = issue.id
        val createdById = createdBy.id
        return TimeEntryDto(id, issueId, description, registerAt, timeSpent, createdById, createdAt, updatedAt)
    }
}