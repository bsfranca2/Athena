package io.github.bsfranca2.athena.exception

data class IssueNotFoundException(val id: Int) : Throwable("Issue $id not found")