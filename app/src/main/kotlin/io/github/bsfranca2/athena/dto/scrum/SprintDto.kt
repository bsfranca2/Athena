package io.github.bsfranca2.athena.dto.scrum

import java.time.LocalDateTime

data class SprintDto(
        val id: Long,
        val boardId: Long,
        val name: String,
        val active: Boolean,
        val startDate: LocalDateTime?,
        val endDate: LocalDateTime?,
        val startedAt: LocalDateTime?,
        val endedAt: LocalDateTime?,
        val createdByUserId: Long
)