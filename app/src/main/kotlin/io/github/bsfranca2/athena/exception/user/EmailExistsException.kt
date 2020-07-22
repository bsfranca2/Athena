package io.github.bsfranca2.athena.exception.user

import io.github.bsfranca2.athena.exception.BadRequestException

data class EmailExistsException(val msg: String) : BadRequestException(msg)