package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Int> {
    fun findByName(name: String?): Role?
}