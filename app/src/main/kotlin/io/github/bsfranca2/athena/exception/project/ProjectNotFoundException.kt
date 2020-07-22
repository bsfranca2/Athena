package io.github.bsfranca2.athena.exception.project

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class ProjectNotFoundException(val id: Long) : EntityNotFoundException("Project", id)