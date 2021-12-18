package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.CategoryModel

class CategoryDataBuilder : AbstractDataBuilder<CategoryModel>() {

	override var name: String? = "Socio Fundador"

	override fun build() = CategoryModel(
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