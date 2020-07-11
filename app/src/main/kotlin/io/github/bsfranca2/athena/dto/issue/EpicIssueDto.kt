package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime

class EpicIssueDto(
        id: Int,
        title: String,
        description: String,
        status: String,
        priority: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        val parentId: Int? = null,
        val children: MutableList<IssueDto> = mutableListOf(),
        createdBy: Int
) : IssueDto(id, IssueType.EPIC, title, description, status, priority, startDate, endDate, createdBy)
