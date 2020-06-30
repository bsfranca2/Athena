package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.TaskAdapter
import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.exception.TaskNotFoundException
import io.github.bsfranca2.athena.repository.TaskRepository
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskService(val userService: UserService, val taskRepository: TaskRepository, val userRepository: UserRepository) {

    fun createTask(taskDto: TaskDto): TaskDto {
        val user = userService.loggedUser
        val task = Task(-1, taskDto.title, taskDto.description, taskDto.status, mutableListOf(), user)
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
        val taskUpdated = task.setTitle(taskUpdate.title).setDescription(taskUpdate.description).setStatus(taskUpdate.status)
        val assignedToUsers = userRepository.findAllById(taskUpdate.assignedTo)
        taskUpdated.assignedTo.clear()
        taskUpdated.assignedTo.addAll(assignedToUsers)
        val taskSaved = taskRepository.save(taskUpdated)
        return TaskAdapter.toDto(taskSaved)
    }

}