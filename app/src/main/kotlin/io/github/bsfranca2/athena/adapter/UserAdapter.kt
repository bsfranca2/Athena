package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.UserDto
import io.github.bsfranca2.athena.entity.User

object UserAdapter {

    fun toDto(user: User): UserDto {
        return UserDto(user.id, user.email, user.password, user.active, user.roles.map { it.name })
    }

}