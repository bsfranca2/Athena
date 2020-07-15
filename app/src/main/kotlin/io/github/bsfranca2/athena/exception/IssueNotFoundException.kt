package io.github.bsfranca2.athena.exception

data class IssueNotFoundException(val id: Long) : Throwable("Issue $id not found")