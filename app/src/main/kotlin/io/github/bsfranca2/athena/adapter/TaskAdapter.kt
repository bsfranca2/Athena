package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.entity.Task

object TaskAdapter {

    fun toDto(task: Task): TaskDto {
        val (id, title, description, status, assignedTo, estimatedTime, createdBy) = task
        val assignedToList = assignedTo.map { it.id }.toMutableList()
        return TaskDto(id, title, description, status, assignedToList, estimatedTime, createdBy.id)
    }

}