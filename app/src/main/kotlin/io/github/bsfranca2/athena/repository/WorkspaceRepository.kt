package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.entity.Workspace
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface WorkspaceRepository : CrudRepository<Workspace, Long> {
    fun findByManagersContains(user: User): MutableList<Workspace>
}