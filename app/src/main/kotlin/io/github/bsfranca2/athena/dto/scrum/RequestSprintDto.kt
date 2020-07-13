package io.github.bsfranca2.athena.dto.scrum

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class RequestSprintDto(
        @field:NotBlank
        val name: String,
        val startDate: LocalDateTime?,
        val endDate: LocalDateTime?,
        val startedAt: LocalDateTime?,
        val endedAt: LocalDateTime?
)