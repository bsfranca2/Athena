package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.PrivilegeDto
import io.github.bsfranca2.athena.dto.RoleDto
import io.github.bsfranca2.athena.entity.Role

object RoleAdapter {

    fun toDto(role: Role): RoleDto {
        val privileges = role.privileges.map { it.name }
        return RoleDto(role.name, privileges.toMutableList())
    }

}