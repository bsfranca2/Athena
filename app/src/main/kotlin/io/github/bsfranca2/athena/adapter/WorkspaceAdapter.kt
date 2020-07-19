package io.github.bsfranca2.athena.adapter

import io.github.bsfranca2.athena.dto.workspace.WorkspaceDto
import io.github.bsfranca2.athena.entity.Workspace

object WorkspaceAdapter {

    fun toDto(workspace: Workspace): WorkspaceDto {
        val (id, name, slug) = workspace
        return WorkspaceDto(id, name, slug)
    }

}