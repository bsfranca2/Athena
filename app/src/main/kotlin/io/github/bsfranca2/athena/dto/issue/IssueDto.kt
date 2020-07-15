package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime
import javax.validation.constraints.*

open class IssueDto(
        val id: Long,
        val type: IssueType,
        @field:NotBlank
        val title: String,
        val description: String,
        @field:NotBlank
        val status: String,
        val priority: Int,
        val startDate: LocalDateTime?,
        val endDate: LocalDateTime?,
        val createdByUserId: Long
)