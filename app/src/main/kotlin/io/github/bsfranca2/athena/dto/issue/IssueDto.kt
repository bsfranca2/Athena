package io.github.bsfranca2.athena.dto.issue

import io.github.bsfranca2.athena.enum.IssueType
import java.time.LocalDateTime
import javax.validation.constraints.*

open class IssueDto(
        val id: Int = -1,
        val type: IssueType = IssueType.DEFAULT,
        val title: String = "",
        val description: String = "",
        val status: String = "",
        val priority: Int = 0,
        val startDate: LocalDateTime? = null,
        val endDate: LocalDateTime? = null,
        val createdBy: Int = -1
)