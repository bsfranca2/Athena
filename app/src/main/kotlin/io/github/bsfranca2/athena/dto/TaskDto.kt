package io.github.bsfranca2.athena.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TaskDto(
        val id: Int = -1,
        @field:NotBlank
        val title: String = "",
        val description: String = "",
        @field:NotBlank
        val status: String = "",
        val assignedTo: MutableList<Int> = mutableListOf(),
        val createdBy: Int = -1
)