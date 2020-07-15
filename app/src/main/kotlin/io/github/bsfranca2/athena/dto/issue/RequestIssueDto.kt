package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class RequestIssueDto(
        val issueType: IssueType = IssueType.DEFAULT,
        @field:NotBlank
        val title: String = "",
        val description: String = "",
        @field:NotBlank
        val status: String = "",
        val priority: Int = 0,
        val startDate: LocalDateTime? = null,
        val endDate: LocalDateTime? = null,
        val estimatedTime: Int = 0,
        val storyPoints: Int = 0,
        val parentId: Long? = null,
        val assignedToUsersId: MutableList<Long> = mutableListOf()
)