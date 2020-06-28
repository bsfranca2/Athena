package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.entity.Task

object TaskAdapter {

    fun toDto(task: Task): TaskDto {
        return TaskDto(task.title, task.description, task.createdBy.id)
    }

}