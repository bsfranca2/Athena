package io.github.bsfranca2.athena.security

import io.github.bsfranca2.athena.security.jwt.JwtTokenProvider
import org.jboss.logging.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SecurityService {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl
    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider
    private val logger = Logger.getLogger(this::class.java)

    fun findLoggedInUsername(): String? {
        return SecurityContextHolder.getContext().getAuthentication().name
    }

    fun login(email: String, password: String) : String {
        var token = ""
        val userDetails = userDetailsService.loadUserByUsername(email)
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken)
            if (usernamePasswordAuthenticationToken.isAuthenticated) {
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                logger.debug(String.format("Auto login %s successfully!", email))
                token = tokenProvider.generateToken(usernamePasswordAuthenticationToken)
            }
        } catch (e: Exception) {
            logger.warn("Authentication error")
        }
        finally {
            return token
        }
    }
}