package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.model.v3.GroupModel

class ContractDataBuilder : AbstractDataBuilder<ContractModel>() {

	var customer: CustomerModel? = CustomerDataBuilder.build { }
	var nameBusiness: String? = ""
	var group: GroupModel? = GroupDataBuilder.build { }
	var addressResidence: AddressModel? = AddressDataBuilder.build { }
	var addressMail: AddressModel? = AddressDataBuilder.build { }

	override fun build() = ContractModel(
		id = id,
		name = name,
		nameBusiness = nameBusiness,
		customer = customer,
		group = group,
		addressResidence = addressResidence,
		addressMail = addressMail,
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