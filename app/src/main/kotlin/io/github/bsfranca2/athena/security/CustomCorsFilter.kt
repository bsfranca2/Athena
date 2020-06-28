package io.github.bsfranca2.athena.security


import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CustomCorsFilter: Filter {

    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        //response.setHeader("Access-Control-Allow-Origin", "http://192.168.15.14:8080")
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "POST, PATCH, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Origin, Content-Type, Version")
        response.setHeader("Access-Control-Expose-Headers", "X-Requested-With, Authorization, Origin, Content-Type")
        val request = req as HttpServletRequest
        if (!request.getMethod().equals("OPTIONS")) {
            chain.doFilter(req, res)
        } else {
            // do not continue with filter chain for options requests
        }
    }

    override fun destroy() {}

    override fun init(filterConfig: FilterConfig) {}

}
