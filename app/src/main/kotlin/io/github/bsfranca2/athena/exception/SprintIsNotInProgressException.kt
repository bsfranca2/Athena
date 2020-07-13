package io.github.bsfranca2.athena.exception

data class SprintIsNotInProgressException(val id: Long) : Throwable("Sprint $id is not in progress")