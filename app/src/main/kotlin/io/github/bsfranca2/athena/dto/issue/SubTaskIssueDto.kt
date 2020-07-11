package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime

class SubTaskIssueDto(
        id: Int,
        title: String,
        description: String,
        status: String,
        priority: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        val estimatedTime: Int = 0,
        val parentId: Int? = null,
        val assignedTo: MutableList<Int> = mutableListOf(),
        createdBy: Int
) : IssueDto(id, IssueType.SUB_TASK, title, description, status, priority, startDate, endDate, createdBy)