package br.com.acalv3.acalapiv3.builder

import java.time.LocalDateTime

abstract class AbstractDataBuilder<U> {

	open var id: Long? = null
	open var name: String? = ""
	open var createdAt: LocalDateTime? = LocalDateTime.now()
	open var lastModifiedAt: LocalDateTime? = LocalDateTime.now()

	abstract fun build(): U

}