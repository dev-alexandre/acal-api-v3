package br.com.acalv3

import br.com.acalv3.domain.exception.RequiredFieldException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class AppAdvice {

	fun getBody(exception: Exception): Map<String, Any?>? {
		val body: MutableMap<String, Any?> = LinkedHashMap()
		body["timestamp"] = LocalDateTime.now()
		body["message"] = exception.message
		return body
	}

	@ExceptionHandler(value = [
		EmptyResultDataAccessException::class,
		NoSuchElementException::class]
	)
	fun e1 (exception: RuntimeException) = run {
		ResponseEntity(getBody(exception),HttpStatus.NO_CONTENT)
	}

	@ExceptionHandler(value = [
		ConstraintViolationException::class,
		RequiredFieldException::class
	])
	fun e2 (exception: RuntimeException) = run {
		ResponseEntity(getBody(exception),HttpStatus.BAD_REQUEST)
	}
}