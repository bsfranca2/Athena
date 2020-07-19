package io.github.bsfranca2.athena.controller

import io.github.bsfranca2.athena.dto.auth.RequestAccountDto
import io.github.bsfranca2.athena.service.InviteService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/invitations")
class InviteController(val inviteService: InviteService) {

    @GetMapping("/{token}")
    fun inviteDetails(@PathVariable token: String)
            = inviteService.inviteDetails(token)

    @PostMapping("/{token}")
    fun acceptInvite(@PathVariable token: String, @RequestBody requestAccountDto: RequestAccountDto?)
            = inviteService.acceptInvite(token, requestAccountDto)

}
