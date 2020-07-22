package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.TesteDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class TesteController {

    @PostMapping("/api/teste")
    fun teste(@Valid @RequestBody testeDto: TesteDto): TesteDto {
        return testeDto
    }

}