package io.github.bsfranca2.athena.exception.issue

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class IssueNotFoundException(val id: Long) : EntityNotFoundException("Issue", id)
