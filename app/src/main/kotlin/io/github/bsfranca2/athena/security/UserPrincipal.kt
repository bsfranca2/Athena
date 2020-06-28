package io.github.bsfranca2.athena.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserPrincipal(
        val userEmail: String = "",
        val userPassword: String = "",
        val userActive: Boolean = false,
        val userRoles: MutableList<GrantedAuthority> = mutableListOf()
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return userRoles
    }

    override fun isEnabled(): Boolean {
        return userActive
    }

    override fun getUsername(): String {
        return userEmail
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}