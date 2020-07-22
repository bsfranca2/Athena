package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class SprintNotFoundException(val id: Long) : EntityNotFoundException("Sprint", id)