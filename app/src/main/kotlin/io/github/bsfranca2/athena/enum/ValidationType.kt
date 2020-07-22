package io.github.bsfranca2.athena.enum

enum class ValidationType(val error: String) {
    NotNull("isNull"),
    NotEmpty("isEmpty"),
    NotBlank("isBlank"),
    Email("emailInvalid")
}