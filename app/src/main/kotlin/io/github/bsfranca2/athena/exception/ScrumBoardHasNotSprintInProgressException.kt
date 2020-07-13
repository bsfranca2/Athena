package io.github.bsfranca2.athena.exception

data class ScrumBoardHasNotSprintInProgressException(val id: Long) : Throwable("Scrum Board $id has not a sprint in progress")