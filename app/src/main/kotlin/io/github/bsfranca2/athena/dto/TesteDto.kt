package io.github.bsfranca2.athena.dto

import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TesteDto(@field:Valid val pessoas: List<PessoaDto>)

data class PessoaDto(@field:Email val email: String, @field:Email @field:NotBlank val secondaryEmail: String)
