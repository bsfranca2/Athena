package io.github.bsfranca2.athena.exception.workspace

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class WorkspaceNotFoundException(val id: Long) : EntityNotFoundException("Workspace", id)
