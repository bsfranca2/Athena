package io.github.bsfranca2.athena.dto.validation

data class ValidationDto(val error: String, val path: List<Any>)