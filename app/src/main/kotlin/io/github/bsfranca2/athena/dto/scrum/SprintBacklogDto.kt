package io.github.bsfranca2.athena.dto.scrum

data class SprintBacklogDto(
        val id: Long,
        val sprintId: Long,
        val items: List<ProductBacklogItemDto>
)