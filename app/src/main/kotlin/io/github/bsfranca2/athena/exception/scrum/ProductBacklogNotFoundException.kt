package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class ProductBacklogNotFoundException(val id: Long) : EntityNotFoundException("Product Backlog", id)