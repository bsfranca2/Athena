package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.AuthenticationDto
import io.github.bsfranca2.athena.dto.LoginResponseDto
import io.github.bsfranca2.athena.dto.UserDto
import io.github.bsfranca2.athena.service.AuthenticationService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(val authenticationService: AuthenticationService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody authenticationDto: AuthenticationDto)
            = authenticationService.registerNewUserAccount(authenticationDto)

    @PostMapping("/login")
    fun login(@Valid @RequestBody authenticationDto: AuthenticationDto)
            = authenticationService.login(authenticationDto)

}