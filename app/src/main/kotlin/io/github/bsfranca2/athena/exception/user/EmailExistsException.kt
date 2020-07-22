package io.github.bsfranca2.athena.exception.user

data class EmailExistsException(val msg: String) : Throwable(msg)