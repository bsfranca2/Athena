package io.github.bsfranca2.athena.exception

data class SprintAlreadyIsInProgressException(val id: Long) : Throwable("Scrum Board $id already has a sprint in progress")