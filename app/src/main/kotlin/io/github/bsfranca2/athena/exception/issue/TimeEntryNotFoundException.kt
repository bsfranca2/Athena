package io.github.bsfranca2.athena.exception.issue

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class TimeEntryNotFoundException(val id: Long) : EntityNotFoundException("Time Entry", id)
