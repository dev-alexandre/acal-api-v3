package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.model.v3.PlaceModel

class PlaceDataBuilder: AbstractDataBuilder<PlaceModel>() {

	var number: String? = "1"
	var letter: String? = "A"
	var address: AddressModel? = AddressDataBuilder.build {}

	override fun build() = PlaceModel(
		id = id,
		number = number,
		letter = letter,
		address = address,
		createdAt = createdAt,
		lastModifiedAt = lastModifiedAt,
	)

	companion object{
		fun build(
			block: (PlaceDataBuilder.()->Unit)? = null
		): PlaceModel = when (block) {
			null -> PlaceDataBuilder().build()
			else -> PlaceDataBuilder().apply(block).build()
		}
	}

}