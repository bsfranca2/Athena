package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.TaskAdapter
import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(val userService: UserService, val taskRepository: TaskRepository) {

    fun createTask(taskDto: TaskDto): TaskDto {
        val user = userService.loggedUser
        val task = Task(taskDto.title, taskDto.description, user)
        val taskSaved = taskRepository.save(task)
        return TaskAdapter.toDto(taskSaved)
    }

    fun listTasks(): List<TaskDto> {
        val user = userService.loggedUser
        val tasks = taskRepository.findByCreatedBy(user)
        return tasks.map { TaskAdapter.toDto(it) }
    }

}