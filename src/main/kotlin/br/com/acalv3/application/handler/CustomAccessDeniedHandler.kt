package br.com.acalv3.application.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler: AccessDeniedHandler {
	override fun handle(
		request: HttpServletRequest?,
		response: HttpServletResponse?,
		accessDeniedException: AccessDeniedException?
	) {
		//val authentication: Authentication = SecurityContextHolder.getContext().authentication
		response?.sendRedirect(request!!.contextPath.toString() + "/403")
	}
}