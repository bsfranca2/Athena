package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.service.TaskService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/tasks")
class TasksController(val taskService: TaskService) {

    @PostMapping
    fun createTask(@Valid @RequestBody taskDto: TaskDto)
            = taskService.createTask(taskDto)

    @GetMapping
    fun listTasks()
            = taskService.listTasks()

}