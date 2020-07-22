package io.github.bsfranca2.athena.exception.scrum

data class ScrumBoardHasNotSprintInProgressException(val id: Long)
    : Throwable("Scrum Board $id has not a sprint in progress")