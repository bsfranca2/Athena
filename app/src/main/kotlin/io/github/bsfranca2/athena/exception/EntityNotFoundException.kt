package io.github.bsfranca2.athena.exception

import org.springframework.http.HttpStatus

open class EntityNotFoundException(
        entity: String,
        entityId: String
): RuntimeException("$entity $entityId not found") {
    constructor(entity: String, entityId: Long): this(entity, entityId.toString())
    val status = HttpStatus.NOT_FOUND
}
