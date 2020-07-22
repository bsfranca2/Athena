package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.UserAdapter
import io.github.bsfranca2.athena.dto.auth.RequestAccountDto
import io.github.bsfranca2.athena.dto.auth.LoginResponseDto
import io.github.bsfranca2.athena.dto.user.UserDto
import io.github.bsfranca2.athena.repository.UserRepository
import io.github.bsfranca2.athena.security.SecurityService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
        private val userRepository: UserRepository,
        private val accountService: AccountService,
        private val securityService: SecurityService,
        private val workspaceService: WorkspaceService
) {
    @Transactional
    fun registerNewUserAccount(requestAccountDto: RequestAccountDto): UserDto {
        val (email, password, workspaceName, workspaceSlug) = requestAccountDto
        val user = userRepository.save(accountService.createUserAccount(email, password))
        if (workspaceName != null && workspaceSlug != null) {
            workspaceService.createUserWorkspace(workspaceName, workspaceSlug, user)
        }
        return UserAdapter.toDto(user)
    }

    fun login(requestAccountDto: RequestAccountDto): LoginResponseDto {
        val (email, password) = requestAccountDto
        val token = securityService.login(email, password)
        if (token.isBlank()) throw Throwable("Credencias invalidas")
        return LoginResponseDto(success = true, token = token)
    }
}