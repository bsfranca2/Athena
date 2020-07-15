package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime

class StoryIssueDto(
        id: Long,
        title: String,
        description: String,
        status: String,
        priority: Int,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        val storyPoints: Int = 0,
        val parentId: Long? = null,
        val children: MutableList<IssueDto> = mutableListOf(),
        val assignedTo: MutableList<Long> = mutableListOf(),
        createdBy: Long
) : IssueDto(id, IssueType.STORY, title, description, status, priority, startDate, endDate, createdBy)