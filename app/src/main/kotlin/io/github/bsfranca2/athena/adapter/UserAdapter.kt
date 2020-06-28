package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.UserDto
import io.github.bsfranca2.athena.entity.User

object UserAdapter {

    fun toDto(user: User): UserDto {
        val roles = user.roles.map { it.name }
        return UserDto(user.email, user.password, user.active, roles)
    }

}