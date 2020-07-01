package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.TaskAdapter
import io.github.bsfranca2.athena.adapter.TimeEntryAdapter
import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.entity.TimeEntry
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.exception.TaskNotFoundException
import io.github.bsfranca2.athena.exception.TimeEntryNotFoundException
import io.github.bsfranca2.athena.exception.UserNotFoundException
import io.github.bsfranca2.athena.repository.TaskRepository
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskService(val userService: UserService, val taskRepository: TaskRepository, val userRepository: UserRepository) {

    fun createTask(taskDto: TaskDto): TaskDto {
        val user = userService.loggedUser
        val (_, title, description, status, priority, assignedToUsersId, startDate, endDate, estimatedTime) = taskDto
        val assignedTo = userRepository.findAllById(assignedToUsersId).toMutableList()
        val timeEntries = mutableListOf<TimeEntry>()
        val task = Task(-1, title, description, status, priority, assignedTo, startDate, endDate, estimatedTime, timeEntries, user)
        val taskSaved = taskRepository.save(task)
        return TaskAdapter.toDto(taskSaved)
    }

    fun listTasks(): List<TaskDto> {
        val user = userService.loggedUser
        val tasks = taskRepository.findByCreatedBy(user)
        return tasks.map { TaskAdapter.toDto(it) }
    }

    fun updateTask(id: Int, taskUpdate: TaskDto): TaskDto {
        val task = taskRepository.findByIdOrNull(id) ?: throw TaskNotFoundException(id)
        task.title = taskUpdate.title
        task.description = taskUpdate.description
        task.status = taskUpdate.status
        task.startDate = taskUpdate.startDate
        task.endDate = taskUpdate.endDate
        task.estimatedTime = taskUpdate.estimatedTime
        val assignedToUsers = userRepository.findAllById(taskUpdate.assignedTo)
        task.assignedTo.clear()
        task.assignedTo.addAll(assignedToUsers)
        val taskSaved = taskRepository.save(task)
        return TaskAdapter.toDto(taskSaved)
    }

    fun deleteTask(id: Int) {
        val task  = taskRepository.findByIdOrNull(id) ?: throw TaskNotFoundException(id)
        return taskRepository.delete(task)
    }

    fun addTimeEntry(taskId: Int, timeEntryDto: TimeEntryDto): List<TimeEntryDto> {
        val createdBy = userService.loggedUser
        val (_, _, description, registerAt, startAt, endAt, _, createdAt) = timeEntryDto
        val task = taskRepository.findByIdOrNull(taskId) ?: throw TaskNotFoundException(taskId)
        val timeEntry = TimeEntry(-1, task, description, registerAt, startAt, endAt, createdBy, createdAt)
        task.timeEntries.add(timeEntry)
        val taskSaved = taskRepository.save(task)
        return taskSaved.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun listTimeEntries(taskId: Int): List<TimeEntryDto> {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw TaskNotFoundException(taskId)
        return task.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun updateTimeEntry(taskId: Int, id: Int, timeEntryDto: TimeEntryDto): List<TimeEntryDto> {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw TaskNotFoundException(taskId)
        val timeEntry = task.timeEntries.find { it.id == id } ?: throw TimeEntryNotFoundException(id)
        val (_, _, newDescription, newRegisterAt, newStartAt, newEndAt) = timeEntryDto
        val timeEntryUpdated = timeEntry.copy()
        timeEntryUpdated.description = newDescription
        timeEntryUpdated.registerAt = newRegisterAt
        timeEntryUpdated.startAt = newStartAt
        timeEntryUpdated.endAt = newEndAt
        timeEntryUpdated.updatedAt = LocalDateTime.now()
        task.timeEntries.remove(timeEntry)
        task.timeEntries.add(timeEntryUpdated)
        val taskSaved = taskRepository.save(task)
        return taskSaved.timeEntries.map { TimeEntryAdapter.toDto(it) }
    }

    fun removeTimeEntry(taskId: Int, id: Int) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw TaskNotFoundException(taskId)
        val timeEntry = task.timeEntries.find { it.id == id } ?: throw TimeEntryNotFoundException(id)
        task.timeEntries.remove(timeEntry)
        taskRepository.save(task)
    }

}