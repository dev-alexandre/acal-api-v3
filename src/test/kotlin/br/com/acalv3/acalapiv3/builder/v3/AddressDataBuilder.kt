package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.model.v3.AddressTypeModel

class AddressDataBuilder: AbstractDataBuilder<AddressModel>() {

	override var name: String? = "Fernando Daltro"
	var addressType: AddressTypeModel? = AddressTypeDataBuilder.build()

	override fun build() = AddressModel(
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