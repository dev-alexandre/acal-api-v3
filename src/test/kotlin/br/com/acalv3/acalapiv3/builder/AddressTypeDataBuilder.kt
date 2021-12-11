package br.com.acalv3.acalapiv3.builder

import br.com.acalv3.domain.model.v3.AddressTypeModel

class AddressTypeDataBuilder {

	var id: Long? = 1
	var name: String = "Avenida"

	private fun build() = AddressTypeModel(
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