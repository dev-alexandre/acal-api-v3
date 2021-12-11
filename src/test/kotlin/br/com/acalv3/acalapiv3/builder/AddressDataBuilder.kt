package br.com.acalv3.acalapiv3.builder

import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.model.v3.AddressTypeModel

class AddressDataBuilder {

	var id: Long? = 1
	var addressType: AddressTypeModel? = AddressTypeDataBuilder.build()
	var name: String = "Fernando Daltro"

	private fun build() = AddressModel(
		id = id,
		addressType = addressType,
		name = name,
	)

	companion object{
		fun build(
			block: (AddressDataBuilder.()->Unit)? = null
		): AddressModel = when (block) {
			null -> AddressDataBuilder().build()
			else ->  AddressDataBuilder().apply(block).build()
		}
	}

}