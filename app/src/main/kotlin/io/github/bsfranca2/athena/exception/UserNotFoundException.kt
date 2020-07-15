package io.github.bsfranca2.athena.exception

data class UserNotFoundException(val id: Long) : Throwable("User $id not found")