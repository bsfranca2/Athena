package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.RoleDto
import io.github.bsfranca2.athena.dto.UserDto
import io.github.bsfranca2.athena.entity.User

object UserAdapter {

    fun toDto(user: User): UserDto {
        val roles = mutableListOf<RoleDto>()
        for (role in user.roles) roles.add(RoleAdapter.toDto(role))
        return UserDto(user.email, user.password, user.active, roles)
    }

}