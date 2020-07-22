package io.github.bsfranca2.athena.exception.validation

import io.github.bsfranca2.athena.enum.ValidationType
import io.github.bsfranca2.athena.exception.BadRequestException

abstract class ValidationException(val type: ValidationType, val path: List<Any>): BadRequestException()
