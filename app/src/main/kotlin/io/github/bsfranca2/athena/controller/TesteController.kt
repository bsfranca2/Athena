package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.TesteDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TesteController {

    @PostMapping("/api/teste/{id}")
    fun teste(@PathVariable id: Long, @Valid @RequestBody testeDto: TesteDto): TesteDto {
        println("Requisição de $id")
        return testeDto
    }

}