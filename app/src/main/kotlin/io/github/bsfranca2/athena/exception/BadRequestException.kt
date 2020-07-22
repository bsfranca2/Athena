package io.github.bsfranca2.athena.exception

import org.springframework.http.HttpStatus

open class BadRequestException(msg: String) : RuntimeException(msg) {
    constructor() : this("")

    val status = HttpStatus.BAD_REQUEST
}