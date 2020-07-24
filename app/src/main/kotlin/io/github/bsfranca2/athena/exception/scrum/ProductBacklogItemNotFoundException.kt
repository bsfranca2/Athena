package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class ProductBacklogItemNotFoundException(val id: Long) : EntityNotFoundException("Product Backlog Item", id)