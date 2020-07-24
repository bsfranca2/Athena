package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class SprintBacklogNotFoundException(val id: Long) : EntityNotFoundException("Sprint Backlog", id)