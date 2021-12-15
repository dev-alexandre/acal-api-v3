package br.com.acalv3.acalapiv3.builder

import br.com.acalv3.domain.model.v3.CategoryModel
import java.time.LocalDateTime

class CategoryDataBuilder {

	var id: Long? = 1
	var name: String = "Socio Fundador"
	var createdAt: LocalDateTime? = LocalDateTime.now()
	var lastModifiedAt: LocalDateTime? = LocalDateTime.now()

	private fun build() = CategoryModel(
		id = id,
		name = name,
		createdAt = createdAt,
		lastModifiedAt = lastModifiedAt,
	)

	companion object{
		fun build(
			block: (CategoryDataBuilder.()->Unit)? = null
		): CategoryModel = when (block) {
			null -> CategoryDataBuilder().build()
			else -> CategoryDataBuilder().apply(block).build()
		}
	}

}