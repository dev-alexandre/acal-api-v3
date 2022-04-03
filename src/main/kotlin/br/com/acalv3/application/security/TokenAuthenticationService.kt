package br.com.acalv3.application.security

import br.com.acalv3.domain.model.v3.UserModel
import br.com.acalv3.domain.response.UserResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.xml.bind.DatatypeConverter

class TokenAuthenticationService(
	private var objectMapper: ObjectMapper
	){

	fun addAuthentication(response: HttpServletResponse, auth: Authentication) {
		val userModel: UserModel = auth.principal as UserModel

		val roles = userModel.roles?.map { it.authority }
		val jwt = Jwts.builder()

			.setSubject(auth.name)
				.claim("role", roles)
				.claim("name", auth.name)
			.setExpiration(
				Date
					.from(LocalDateTime.now()
						.plusHours(HOURS_TO_EXPIRATION)
						.atZone(ZoneId.systemDefault()).toInstant())
			).signWith(
				SignatureAlgorithm.HS512,
				SecretKeySpec(
					DatatypeConverter.parseBase64Binary(SECRET),
					SignatureAlgorithm.HS512.jcaName
				)
			)

		val token = jwt.compact()
		userModel.token = token

		val stringResponse = objectMapper.writeValueAsString(UserResponse(
			username = userModel.username,
			token = token,
			roles = roles
		))

		try {
			response.writer.write(stringResponse)
			response.writer.flush()
			response.writer.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	@Throws(ExpiredJwtException::class)
	fun getAuthentication(request: HttpServletRequest): Authentication? {
		val token = request.getHeader(HEADER_STRING)
		try {
			if (token != null) {
				val user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.body
					.subject
				if (user != null) {
					return UsernamePasswordAuthenticationToken(user, null, Collections.emptyList())
				}
			}
		} catch (e: IllegalArgumentException ) {
			throw RuntimeException(ERROR_MESSAGE)
		} catch (e: ExpiredJwtException) {
			throw RuntimeException(ERROR_MESSAGE)
		}
		return null
	}

	companion object{
		private const val ERROR_MESSAGE = "É, você não possui permissão"
		private const val HOURS_TO_EXPIRATION: Long = 8
		private const val SECRET = "2a108Imja6xTLcqhRUDVrVusTuW7xVtjifhrJon1bDyBkdRzuLSHBVY2q"
		private const val TOKEN_PREFIX = "Bearer"
		private const val HEADER_STRING = "Authorization"
	}

}