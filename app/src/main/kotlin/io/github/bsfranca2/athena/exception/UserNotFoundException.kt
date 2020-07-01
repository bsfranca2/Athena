package io.github.bsfranca2.athena.exception

data class UserNotFoundException(val id: Int) : Throwable("User $id not found")