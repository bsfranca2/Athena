package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.dto.issue.*
import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.TimeEntry
import io.github.bsfranca2.athena.enum.IssueType

object IssueAdapter {

    fun toDto(issue: Issue): IssueDto {
        val (id, issueType, title, description, status, priority, startDate, endDate, estimatedTime, storyPoints, _, _, _, createdBy) = issue
        val parent = issue.parent?.id
        val children = issue.children.map { toDto(it) }.toMutableList()
        val assignedTo = issue.assignedTo.map { it.id }.toMutableList()
        return when(issueType) {
            IssueType.EPIC -> EpicIssueDto(id, title, description, status, priority, startDate, endDate, parent, children, createdBy.id)
            IssueType.BUG -> BugIssueDto(id, title, description, status, priority, startDate, endDate, parent, children, assignedTo, createdBy.id)
            IssueType.STORY -> StoryIssueDto(id, title, description, status, priority, startDate, endDate, storyPoints, parent, children, assignedTo, createdBy.id)
            IssueType.SUB_TASK -> SubTaskIssueDto(id, title, description, status, priority, startDate, endDate, estimatedTime, parent, assignedTo, createdBy.id)
            else -> IssueDto(id, issueType, title, description, status, priority, startDate, endDate, createdBy.id)
        }
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