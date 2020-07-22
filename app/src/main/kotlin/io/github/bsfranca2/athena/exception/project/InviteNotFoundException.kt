package io.github.bsfranca2.athena.exception.project

import io.github.bsfranca2.athena.exception.EntityNotFoundException

data class InviteNotFoundException(val token: String) : EntityNotFoundException("Invite", token)
