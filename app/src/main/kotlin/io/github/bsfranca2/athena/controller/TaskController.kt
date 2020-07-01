package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.TaskDto
import io.github.bsfranca2.athena.dto.TimeEntryDto
import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/tasks")
class TaskController(val taskService: TaskService) {

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@Valid @RequestBody taskDto: TaskDto)
            = taskService.createTask(taskDto)

    @GetMapping
    fun listTasks()
            = taskService.listTasks()

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Int, @Valid @RequestBody taskUpdate: TaskDto)
            = taskService.updateTask(id, taskUpdate)

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Int)
            = taskService.deleteTask(id)

    @PostMapping("/{id}/time-entries") @ResponseStatus(HttpStatus.CREATED)
    fun addTimeEntry(@PathVariable id: Int, @RequestBody timeEntryDto: TimeEntryDto)
            = taskService.addTimeEntry(id, timeEntryDto)

    @GetMapping("/{id}/time-entries")
    fun listTimeEntries(@PathVariable id: Int)
            = taskService.listTimeEntries(id)

}