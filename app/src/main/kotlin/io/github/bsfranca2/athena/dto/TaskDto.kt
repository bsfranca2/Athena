package io.github.bsfranca2.athena.dto

import javax.validation.constraints.*

data class TaskDto(
        val id: Int = -1,
        @field:NotBlank
        val title: String = "",
        val description: String = "",
        @field:NotBlank
        val status: String = "",
        val assignedTo: MutableList<Int> = mutableListOf(),
        @field:Min(0)
        val estimatedTime: Int = 0,
        val createdBy: Int = -1
)