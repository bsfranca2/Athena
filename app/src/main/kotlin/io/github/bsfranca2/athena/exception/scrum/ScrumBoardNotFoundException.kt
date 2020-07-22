package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class ScrumBoardNotFoundException(val id: Long) : EntityNotFoundException("Scrum Board", id)