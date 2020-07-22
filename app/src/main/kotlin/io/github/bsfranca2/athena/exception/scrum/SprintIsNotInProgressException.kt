package io.github.bsfranca2.athena.exception.scrum

import io.github.bsfranca2.athena.exception.BadRequestException

data class SprintIsNotInProgressException(val id: Long) : BadRequestException("Sprint $id is not in progress")