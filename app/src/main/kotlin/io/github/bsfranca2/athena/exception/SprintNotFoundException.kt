package io.github.bsfranca2.athena.exception

data class SprintNotFoundException(val id: Long) : Throwable("Sprint $id not found")