package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.service.TaskService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/tasks")
class TaskController(val taskService: TaskService) {

    @PostMapping
    fun createTask(@Valid @RequestBody taskDto: TaskDto)
            = taskService.createTask(taskDto)

    @GetMapping
    fun listTasks()
            = taskService.listTasks()

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Int, @Valid @RequestBody taskUpdate: TaskDto)
            = taskService.updateTask(id, taskUpdate)

    @PostMapping("/{id}/time-entries")
    fun addTimeEntry(@PathVariable id: Int, @RequestBody timeEntryDto: TimeEntryDto)
            = taskService.addTimeEntry(id, timeEntryDto)

    @GetMapping("/{id}/time-entries")
    fun listTimeEntries(@PathVariable id: Int)
            = taskService.listTimeEntries(id)

}