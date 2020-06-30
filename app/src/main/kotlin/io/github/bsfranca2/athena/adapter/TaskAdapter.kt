package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.entity.Task

object TaskAdapter {

    fun toDto(task: Task): TaskDto {
        val assignedTo = task.assignedTo.map { it.id }.toMutableList()
        return TaskDto(task.id, task.title, task.description, task.status, assignedTo, task.createdBy.id)
    }

}