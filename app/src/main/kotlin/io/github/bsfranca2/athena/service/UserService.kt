package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.repository.UserRepository
import io.github.bsfranca2.athena.security.UserPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    val loggedUser: User
            get() {
                val principal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
                return userRepository.findByEmail(principal.userEmail)!!
            }

}