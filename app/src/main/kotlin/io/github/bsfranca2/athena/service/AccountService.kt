package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.AccountAdapter
import io.github.bsfranca2.athena.dto.auth.AccountDto
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.repository.RoleRepository
import io.github.bsfranca2.athena.repository.UserRepository
import io.github.bsfranca2.athena.repository.WorkspaceRepository
import io.github.bsfranca2.athena.security.UserPrincipal
import io.github.bsfranca2.athena.exception.user.EmailExistsException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
        val userRepository: UserRepository,
        val passwordEncoder: PasswordEncoder,
        val roleRepository: RoleRepository,
        val workspaceRepository: WorkspaceRepository
) {

    val loggedUser: User
        get() {
            val principal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
            return userRepository.findByEmail(principal.userEmail)!!
        }

    fun createUserAccount(email: String, password: String): User {
        if (emailExists(email))
            throw EmailExistsException("Já existe uma conta com o email: " + email)
        val userRole = roleRepository.findByName("ROLE_USER")
                ?: throw Throwable("Não foi encontrado a função de usuário")
        val roles = mutableListOf(userRole)
        val active = true
        return User(-1, email, passwordEncoder.encode(password), active, roles)
    }

    private fun emailExists(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    fun accountInfo(): AccountDto {
        val user = loggedUser
        val workspaces = workspaceRepository.findByManagersContains(user)
        return AccountAdapter.toDto(user, workspaces)
    }
}