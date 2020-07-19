package io.github.bsfranca2.athena.exception

data class WorkspaceNotFoundException(val id: Long) : Throwable("Workspace $id not found")