package io.github.bsfranca2.athena.exception

data class ScrumBoardNotFoundException(val id: Long) : Throwable("Scrum Board $id not found")