package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.AddressTypeModel

class AddressTypeDataBuilder: AbstractDataBuilder<AddressTypeModel>() {

	override fun build() = AddressTypeModel(
		id = id,
		name = name,
	)

	companion object{
		fun build(
			block: (AddressTypeDataBuilder.()->Unit)? = null
		): AddressTypeModel = when (block) {
			null -> AddressTypeDataBuilder().build()
			else ->  AddressTypeDataBuilder().apply(block).build()
		}
	}
}