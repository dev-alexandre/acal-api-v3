package br.com.acalv3.acalapiv3.builder.v3

import br.com.acalv3.acalapiv3.builder.AbstractDataBuilder
import br.com.acalv3.domain.model.v3.CustomerModel
import java.time.LocalDate

class CustomerDataBuilder : AbstractDataBuilder<CustomerModel>() {

	override var name: String? = "Alexandre"

	var businessName: String? = "NCC enterprise"
	var document: String? = "03396885562"
	var phoneNumber: String? = "71988872479"
	var birthDate: LocalDate? = LocalDate.of(1987,5,20)

	override fun build() = CustomerModel(
		id = id,
		name = name,
		businessName = businessName,
		document = document,
		phoneNumber = phoneNumber,
		birthDate = birthDate,
		createdAt = createdAt,
		lastModifiedAt = lastModifiedAt,
	)

	companion object{
		fun build(
			block: (CustomerDataBuilder.()->Unit)? = null
		): CustomerModel = when (block) {
			null -> CustomerDataBuilder().build()
			else ->  CustomerDataBuilder().apply(block).build()
		}
	}

}