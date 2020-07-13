package io.github.bsfranca2.athena.exception

data class ProjectNotFoundException(val id: Long) : Throwable("Project $id not found")