package io.github.bsfranca2.athena.exception.scrum

data class SprintIsNotInProgressException(val id: Long) : Throwable("Sprint $id is not in progress")