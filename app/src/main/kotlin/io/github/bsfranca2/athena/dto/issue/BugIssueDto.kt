package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime

class BugIssueDto(
        id: Long,
        title: String,
        description: String,
        status: String,
        priority: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        val parentId: Long?,
        val childrenId: MutableList<IssueDto>,
        val assignedToUsersId: MutableList<Long>,
        createdByUserId: Long
) : IssueDto(id, IssueType.BUG, title, description, status, priority, startDate, endDate, createdByUserId)