package io.github.bsfranca2.athena.security

import io.github.bsfranca2.athena.entity.Privilege
import io.github.bsfranca2.athena.entity.Role
import io.github.bsfranca2.athena.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email) ?:
            return UserPrincipal("", "", true, getAuthorities(mutableListOf()))
        return UserPrincipal(user.email, user.password, user.active, getAuthorities(user.roles))
    }

    private fun getAuthorities(roles: MutableList<Role>): MutableList<GrantedAuthority> {
        return getGrantedAuthorities(getPrivileges(roles))
    }

    private fun getPrivileges(roles: MutableList<Role>): MutableList<String> {
        val privileges = mutableListOf<String>()
        val collection = mutableListOf<Privilege>()
        for (role in roles) collection.addAll(role.privileges)
        for (item in collection) privileges.add(item.name)
        return privileges
    }

    private fun getGrantedAuthorities(privileges: List<String>): MutableList<GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        for (privilege in privileges) authorities.add(SimpleGrantedAuthority(privilege))
        return authorities
    }

}