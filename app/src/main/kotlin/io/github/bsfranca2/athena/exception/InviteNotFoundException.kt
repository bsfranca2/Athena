package io.github.bsfranca2.athena.exception

data class InviteNotFoundException(val token: String)
    : Throwable("Invite with the token $token not found")