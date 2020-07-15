package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime
import javax.validation.constraints.*

open class IssueDto(
        val id: Long = -1,
        val type: IssueType = IssueType.DEFAULT,
        @field:NotBlank
        val title: String = "",
        val description: String = "",
        @field:NotBlank
        val status: String = "",
        val priority: Int = 0,
        val startDate: LocalDateTime? = null,
        val endDate: LocalDateTime? = null,
        val createdBy: Long = -1
)