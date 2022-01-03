package br.com.acalv3

import br.com.acalv3.domain.exception.RequiredFieldException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLException
import java.time.LocalDateTime

@ControllerAdvice
class AppAdvice {

	fun getBody(ex: Exception): Map<String, Any?>? {
		val body: MutableMap<String, Any?> = LinkedHashMap()
		body["timestamp"] = LocalDateTime.now()
		body["error"] = ex.message
		return body
	}

	fun getBody(ex: Exception, error: String): Map<String, Any?>? {
		val body: MutableMap<String, Any?> = LinkedHashMap()
		body["timestamp"] = LocalDateTime.now()
		body["error"] =  error
		return body
	}

	@ExceptionHandler(value = [
		EmptyResultDataAccessException::class,
		NoSuchElementException::class]
	)
	fun e1 (ex: RuntimeException) = run {
		ResponseEntity(getBody(ex),HttpStatus.NO_CONTENT)
	}

	@ExceptionHandler(value = [
		ConstraintViolationException::class,
	])
	fun e2 (ex: RuntimeException) = run {
		ResponseEntity(
			getBody(ex),
			HttpStatus.BAD_REQUEST
		)
	}

	/*
	*@TODO
	*  melhorar isso aqui
	* **/
	@ExceptionHandler(value = [
		RequiredFieldException::class
	])
	fun e3 (ex: SQLException) = run {

		val start = ex.message?.indexOf("=(", 0, false)?.plus(2)
		val end = ex.message?.lastIndexOf(")")

		val error = ex.message?.subSequence(
			start!!,
			end!!
		).toString()

		ResponseEntity(
			getBody(ex, error),
			HttpStatus.BAD_REQUEST
		)
	}
}