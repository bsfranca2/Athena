package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.Privilege
import org.springframework.data.repository.CrudRepository

interface PrivilegeRepository : CrudRepository<Privilege, Int> {
    fun findByName(name: String?): Privilege?
}