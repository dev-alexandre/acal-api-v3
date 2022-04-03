package br.com.acalv3.application.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler: AccessDeniedHandler {
	private var logger: Logger = LoggerFactory.getLogger(CustomAccessDeniedHandler::class.java)

	override fun handle(
		request: HttpServletRequest?,
		response: HttpServletResponse?,
		accessDeniedException: AccessDeniedException?
	) {
		logger.info("denied access")

		response?.sendRedirect(request!!.contextPath.toString() + "/403")
	}
}