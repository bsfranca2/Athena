package io.github.bsfranca2.athena.security.jwt


import io.github.bsfranca2.athena.security.UserPrincipal
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider {

    val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    @Value("\${app.jwtSecret}")
    private lateinit var jwtSecret: String

    @Value("\${app.jwtExpirationInMs}")
    private lateinit var jwtExpirationInMs: String

    fun generateToken(authentication: Authentication): String {

        val userPrincipal = authentication.getPrincipal() as UserPrincipal

        val now = Date()

        /**
         * Vou concatenar o tenant e o username para permitir
         * que tenhamos usuários repetidos para tenants diferentes.
         */

        return Jwts.builder()
                .setSubject("${userPrincipal.username}")
                .setIssuedAt(Date())
                .setExpiration(Date(now.getTime().plus(jwtExpirationInMs.toInt())))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun getUserIdFromJWT(token: String): String? {
        val claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body

        return claims.subject
    }

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }

        return false
    }
}
