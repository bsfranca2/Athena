package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.auth.AccountDto
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.entity.Workspace

object AccountAdapter {
    fun toDto(user: User, workspaces: MutableList<Workspace>): AccountDto {
        val userDto = UserAdapter.toDto(user)
        val workspacesDto = workspaces.map { WorkspaceAdapter.toDto(it) }
        return AccountDto(userDto, workspacesDto)
    }
}