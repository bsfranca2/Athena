package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.auth.RequestAccountDto
import io.github.bsfranca2.athena.service.AuthenticationService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(val authenticationService: AuthenticationService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody requestAccountDto: RequestAccountDto)
            = authenticationService.registerNewUserAccount(requestAccountDto)

    @PostMapping("/login")
    fun login(@Valid @RequestBody requestAccountDto: RequestAccountDto)
            = authenticationService.login(requestAccountDto)

}