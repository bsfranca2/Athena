package io.github.bsfranca2.athena.dto.issue

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class TimeEntryDto(
        val id: Long,
        val issue: Long,
        val description: String,
        val registerAt: LocalDateTime,
        val timeSpent: Int,
        val createdBy: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime?
)