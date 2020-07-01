package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.entity.TimeEntry

object TaskAdapter {

    fun toDto(task: Task): TaskDto {
        val (id, title, description, status, priority, assignedTo, startDate, endDate, estimatedTime, _, createdBy) = task
        val assignedToList = assignedTo.map { it.id }.toMutableList()
        return TaskDto(id, title, description, status, priority, assignedToList, startDate, endDate, estimatedTime, createdBy.id)
    }

}

object TimeEntryAdapter {
    fun toDto(timeEntry: TimeEntry): TimeEntryDto {
        val (id, task, description, registerAt, startAt, endAt, createdBy, createdAt, updatedAt) = timeEntry
        val taskId = task.id
        val createdById = createdBy.id
        return TimeEntryDto(id, taskId, description, registerAt, startAt, endAt, createdById, createdAt, updatedAt)
    }
}