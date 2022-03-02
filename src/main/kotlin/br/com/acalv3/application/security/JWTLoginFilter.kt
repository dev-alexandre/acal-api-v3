package br.com.acalv3.application.security

import br.com.acalv3.domain.model.v3.UserModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter(
	url: String = "/login",
	private val objectMapper: ObjectMapper,
	private val authManager: AuthenticationManager,
	private val tokenAuthenticationService: TokenAuthenticationService,
	) :
	AbstractAuthenticationProcessingFilter(
		AntPathRequestMatcher(url)
	) {

	override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
		val credentials: UserModel =  objectMapper.readValue(request?.inputStream, UserModel().javaClass)

		val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
			credentials.username,
			credentials.password,
			credentials.authorities,
		)

		return authManager.authenticate(usernamePasswordAuthenticationToken)
	}

	override fun successfulAuthentication(
		request: HttpServletRequest?,
		response: HttpServletResponse,
		chain: FilterChain?,
		authResult: Authentication
	) {
		return tokenAuthenticationService.addAuthentication(response, authResult)
	}

}