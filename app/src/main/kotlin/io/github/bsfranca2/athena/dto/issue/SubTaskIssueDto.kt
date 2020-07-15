package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime

class SubTaskIssueDto(
        id: Long,
        title: String,
        description: String,
        status: String,
        priority: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        val estimatedTime: Int,
        val parentId: Long?,
        val assignedToUsersId: MutableList<Long>,
        createdByUserId: Long
) : IssueDto(id, IssueType.SUB_TASK, title, description, status, priority, startDate, endDate, createdByUserId)