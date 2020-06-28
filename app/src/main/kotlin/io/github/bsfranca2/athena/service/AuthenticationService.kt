package io.github.bsfranca2.athena.service

import io.github.bsfranca2.athena.adapter.UserAdapter
import io.github.bsfranca2.athena.dto.AuthenticationDto
import io.github.bsfranca2.athena.dto.LoginResponseDto
import io.github.bsfranca2.athena.dto.UserDto
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.repository.RoleRepository
import io.github.bsfranca2.athena.repository.UserRepository
import io.github.bsfranca2.athena.security.SecurityService
import io.github.bsfranca2.athena.validation.EmailExistsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
        val userRepository: UserRepository,
        val passwordEncoder: PasswordEncoder,
        val roleRepository: RoleRepository,
        val securityService: SecurityService
) {

    fun registerNewUserAccount(authenticationDto: AuthenticationDto): UserDto {
        val (email, password) = authenticationDto
        if (emailExists(email)) {
            throw EmailExistsException("Já existe uma conta com o email: " + email)
        }
        val active = true
        val userRole = roleRepository.findByName("ROLE_USER") ?: throw Throwable("Não foi encontrado a função de usuário")
        val roles = mutableListOf(userRole)
        val user = User(email, passwordEncoder.encode(password), active, roles)
        return UserAdapter.toDto(userRepository.save(user))
    }

    private fun emailExists(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    fun login(authenticationDto: AuthenticationDto): LoginResponseDto {
        val (email, password) = authenticationDto
        val token = securityService.login(email, password)
        if (token.isBlank()) throw Throwable("Credencias invalidas")
        return LoginResponseDto(success = true, token = token)
    }
}