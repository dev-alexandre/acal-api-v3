package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.enumeration.PersonTypeEnum
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.repository.v3.CustomerRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.CustomerSpec
import org.springframework.stereotype.Service

@Service
class CustomerService(
	val repository: CustomerRepository,
): AppService<CustomerModel>(repository, repository) {

    override fun findByName(name: String): CustomerModel =
        repository.findByName(name)

	override fun prepareForSave(u: CustomerModel) {
		super.prepareForSave(u)

		when(u.document?.length) {
			11 -> u.personType = PersonTypeEnum.PHYSICAL
			else -> u.personType = PersonTypeEnum.LEGAL
		}

	}

	override fun pageable(filter: FilterDTO<CustomerModel>)  =
		repository.findAll(

			CustomerSpec(
				filter.model
			),

			getPage(filter)
		)
}
