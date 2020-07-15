package io.github.bsfranca2.athena.exception

data class TimeEntryNotFoundException(val id: Long) : Throwable("Time entry $id not found")