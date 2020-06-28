package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.adapter.UserAdapter
import io.github.bsfranca2.athena.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/check")
class CheckController(val userService: UserService) {

    @GetMapping
    fun check()
            = UserAdapter.toDto(userService.loggedUser)

}