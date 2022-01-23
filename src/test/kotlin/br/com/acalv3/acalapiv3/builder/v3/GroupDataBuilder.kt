package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.enumeration.CategoryEnum
import br.com.acalv3.domain.model.v3.GroupModel

class GroupDataBuilder: AbstractDataBuilder<GroupModel>() {

	override var name: String? = "Comercio"
	var monetaryValue: Double? = 0.00

	override fun build() = GroupModel(
		id = id,
		name = name,
		monetaryValue = monetaryValue,
		category = CategoryEnum.EFFECTIVE,
		createdAt = createdAt,
		lastModifiedAt = lastModifiedAt,
	)

	companion object{
		fun build(
			block: (GroupDataBuilder.()->Unit)? = null
		): GroupModel = when (block) {
			null -> GroupDataBuilder().build()
			else -> GroupDataBuilder().apply(block).build()
		}
	}

}