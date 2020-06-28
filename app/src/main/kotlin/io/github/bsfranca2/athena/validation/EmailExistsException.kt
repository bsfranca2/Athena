package io.github.bsfranca2.athena.validation

data class EmailExistsException(val msg: String) : Throwable(msg)