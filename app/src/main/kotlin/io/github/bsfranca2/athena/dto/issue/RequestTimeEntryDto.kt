package io.github.bsfranca2.athena.dto.issue

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class RequestTimeEntryDto(
        val description: String = "",
        val registerAt: LocalDateTime = LocalDateTime.now(),
        val timeSpent: Int = 0
)