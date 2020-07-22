package io.github.bsfranca2.athena.exception.user

data class UserNotFoundException(val id: Long) : Throwable("User $id not found")