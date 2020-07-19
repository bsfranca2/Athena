package io.github.bsfranca2.athena.dto.auth

import io.github.bsfranca2.athena.dto.user.UserDto
import io.github.bsfranca2.athena.dto.workspace.WorkspaceDto

data class AccountDto(
        val user: UserDto,
        val workspaces: List<WorkspaceDto>
)