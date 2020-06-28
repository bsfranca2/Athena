package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Task
import io.github.bsfranca2.athena.entity.User
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Int> {
    fun findByCreatedBy(createdBy: User): MutableList<Task>
}