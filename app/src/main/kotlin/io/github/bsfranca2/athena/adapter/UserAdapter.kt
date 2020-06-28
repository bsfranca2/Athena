package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.UserDto
import io.github.bsfranca2.athena.entity.User

object UserAdapter {

    fun toDto(user: User): UserDto {
        val roles: MutableMap<String, List<String>> = mutableMapOf()
        for (role in user.roles) roles.put(role.name, role.privileges.map { it.name })
        return UserDto(user.email, user.password, user.active, roles)
    }

}