package io.github.bsfranca2.athena.dto.issue

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class TimeEntryDto(
        val id: Long = -1,
        @field:Min(1)
        val issue: Long = -1,
        val description: String = "",
        val registerAt: LocalDateTime = LocalDateTime.now(),
        val timeSpent: Int = 0,
        @field:Min(1)
        val createdBy: Long = -1,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime? = null
)