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

    @PostMapping("/{taskId}/time-entries") @ResponseStatus(HttpStatus.CREATED)
    fun addTimeEntry(@PathVariable taskId: Int, @RequestBody timeEntryDto: TimeEntryDto)
            = taskService.addTimeEntry(taskId, timeEntryDto)

    @GetMapping("/{taskId}/time-entries")
    fun listTimeEntries(@PathVariable taskId: Int)
            = taskService.listTimeEntries(taskId)

    @PutMapping("/{taskId}/time-entries/{id}")
    fun updateTimeEntry(@PathVariable taskId: Int, @PathVariable id: Int, @Valid @RequestBody timeEntryDto: TimeEntryDto)
            = taskService.updateTimeEntry(taskId, id, timeEntryDto)

    @DeleteMapping("/{taskId}/time-entries/{id}")
    fun removeTimeEntry(@PathVariable taskId: Int, @PathVariable id: Int)
            = taskService.removeTimeEntry(taskId, id)

}