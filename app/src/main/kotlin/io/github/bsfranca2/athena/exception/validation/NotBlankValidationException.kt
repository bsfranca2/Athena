package io.github.bsfranca2.athena.exception.validation

import io.github.bsfranca2.athena.enum.ValidationType

class NotBlankValidationException(path: List<Any>) : ValidationException(ValidationType.NotBlank, path)
