package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.service.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(val accountService: AccountService) {

    @GetMapping
    fun accountInfo()
            = accountService.accountInfo()

    @GetMapping("/invitations")
    fun invitations() {}

}