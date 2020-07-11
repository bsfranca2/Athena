package io.github.bsfranca2.athena.dto

import java.time.LocalDateTime
import javax.validation.constraints.*

data class IssueDto(
        val id: Int = -1,
        @field:NotBlank
        val title: String = "",
        val description: String = "",
        @field:NotBlank
        val status: String = "",
        val priority: Int = 0,
        val assignedTo: MutableList<Int> = mutableListOf(),
        val startDate: LocalDateTime? = null,
        val endDate: LocalDateTime? = null,
        @field:Min(0)
        val estimatedTime: Int = 0,
        val createdBy: Int = -1
)