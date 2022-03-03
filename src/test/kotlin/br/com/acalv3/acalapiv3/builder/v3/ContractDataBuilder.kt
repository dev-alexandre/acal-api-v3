package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.model.v3.GroupModel
import br.com.acalv3.domain.model.v3.PlaceModel

class ContractDataBuilder : AbstractDataBuilder<ContractModel>() {

	override var name: String? = "2020001"

	var customer: CustomerModel? = CustomerDataBuilder.build { }
	var group: GroupModel? = GroupDataBuilder.build { }
	private var nameBusiness: String? = ""
	var placeResidence: PlaceModel? = PlaceDataBuilder.build { }
	var placeMail: PlaceModel? = PlaceDataBuilder.build { }

	override fun build() = ContractModel(
		id = id,
		name = name,
		nameBusiness = nameBusiness,
		customer = customer,
		group = group,
		placeResidence = placeResidence,
		placeMail = placeMail,
		createdAt = createdAt,
		lastModifiedAt = lastModifiedAt,
	)

	companion object{
		fun build(
			block: (ContractDataBuilder.()->Unit)? = null
		): ContractModel = when (block) {
			null -> ContractDataBuilder().build()
			else ->  ContractDataBuilder().apply(block).build()
		}
	}

}