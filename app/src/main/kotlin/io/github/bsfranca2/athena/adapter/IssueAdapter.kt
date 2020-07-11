package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.dto.issue.*
import io.github.bsfranca2.athena.entity.Issue
import io.github.bsfranca2.athena.entity.TimeEntry
import io.github.bsfranca2.athena.enum.IssueType

object IssueAdapter {

    fun toDto(issue: Issue): IssueDto {
        val (id, issueType, title, description, status, priority, startDate, endDate) = issue
        val createdBy = issue.createdBy
        val parent = issue.parent?.id
        val children = issue.children.map { toDto(it) }.toMutableList()
        val assignedTo = issue.assignedTo.map { it.id }.toMutableList()
        if (issueType == IssueType.EPIC) {
            return EpicIssueDto(id, title, description, status, priority, startDate, endDate, parent, children, createdBy.id)
        } else if (issueType == IssueType.STORY) {
            val storyPoints = issue.storyPoints
            return StoryIssueDto(id, title, description, status, priority, startDate, endDate, storyPoints, parent, children, assignedTo, createdBy.id)
        } else if (issueType == IssueType.BUG) {
            return BugIssueDto(id, title, description, status, priority, startDate, endDate, parent, children, assignedTo, createdBy.id)
        } else if (issueType == IssueType.SUB_TASK) {
            val estimatedTime = issue.estimatedTime
            return SubTaskIssueDto(id, title, description, status, priority, startDate, endDate, estimatedTime, parent, assignedTo, createdBy.id)
        }
        return IssueDto(id, issueType, title, description, status, priority, startDate, endDate, createdBy.id)
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