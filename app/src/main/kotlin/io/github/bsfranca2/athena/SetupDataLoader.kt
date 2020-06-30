package io.github.bsfranca2.athena

import io.github.bsfranca2.athena.entity.Privilege
import io.github.bsfranca2.athena.entity.Role
import io.github.bsfranca2.athena.entity.User
import io.github.bsfranca2.athena.repository.PrivilegeRepository
import io.github.bsfranca2.athena.repository.RoleRepository
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class SetupDataLoader : ApplicationListener<ContextRefreshedEvent> {
    private var alreadySetup = false
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var roleRepository: RoleRepository
    @Autowired
    lateinit var privilegeRepository: PrivilegeRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) return

        val readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE")
        val writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE")

        val adminPrivileges = mutableListOf(readPrivilege, writePrivilege)
        val userPrivileges = mutableListOf(readPrivilege)

        val adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges)
        createRoleIfNotFound("ROLE_USER", userPrivileges)

        val email = "admin@athena.io"
        val password = passwordEncoder.encode("secret")
        val roles = mutableListOf(adminRole)
        createUserIfNotFound(email, password, roles)

        alreadySetup = true
    }

    @Transactional
    fun createPrivilegeIfNotFound(name: String): Privilege {
        var privilege = privilegeRepository.findByName(name)
        if (privilege == null) {
            privilege = Privilege(name = name)
            privilege = privilegeRepository.save(privilege)
        }
        return privilege
    }

    @Transactional
    fun createRoleIfNotFound(name: String, privileges: MutableList<Privilege>): Role {
        var role = roleRepository.findByName(name)
        if (role == null) {
            role = Role(name = name, privileges = privileges)
            role = roleRepository.save(role)
        }
        return role
    }


    @Transactional
    fun createUserIfNotFound(email: String, password: String, roles: MutableList<Role>): User {
        var user = userRepository.findByEmail(email)
        if (user == null) {
            user = User(-1, email, password, true, roles)
            user = userRepository.save(user)
        }
        return user
    }

}
