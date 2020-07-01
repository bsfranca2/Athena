package io.github.bsfranca2.athena.dto

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class TimeEntryDto(
        val id: Int = -1,
        @field:Min(1)
        val task: Int = -1,
        val description: String = "",
        val registerAt: LocalDateTime = LocalDateTime.now(),
        val startAt: Int = 0,
        val endAt: Int = 0,
        @field:Min(1)
        val createdBy: Int = -1,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime? = null
)