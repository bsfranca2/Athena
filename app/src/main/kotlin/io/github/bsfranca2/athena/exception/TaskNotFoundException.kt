package io.github.bsfranca2.athena.exception

data class TaskNotFoundException(val id: Int) : Throwable("Task $id not found")