package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.BadRequestException

data class ScrumBoardHasNotSprintInProgressException(val id: Long)
    : BadRequestException("Scrum Board $id has not a sprint in progress")