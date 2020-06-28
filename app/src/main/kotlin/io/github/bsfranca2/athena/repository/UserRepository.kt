package io.github.bsfranca2.athena.repository

import io.github.bsfranca2.athena.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String?): User?
}