package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.model.v3.AddressTypeModel
import br.com.acalv3.domain.repository.v3.AddressTypeRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.AddressTypeSpec
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class AddressTypeService(
	val repository: AddressTypeRepository,
): AppService<AddressTypeModel>(repository) {

    override fun findByName(name: String): AddressTypeModel =
        repository.findByName(name)

	override fun pageable(filter: FilterDTO<AddressTypeModel>)  =
		repository.findAll(

			AddressTypeSpec(
				filter.model
			),

			getPage(filter)

		)

	private fun getPage(filter: FilterDTO<AddressTypeModel>) : PageRequest {

		return when (filter.sort){
			null -> PageRequest.of(
				filter.page.number,
				filter.page.size,
			)
			else -> {
				PageRequest.of(
					filter.page.number,
					filter.page.size,
					getOrderDirection(filter)
				)
			}
		}

	}

	private fun getOrderDirection(filter: FilterDTO<AddressTypeModel>): Sort {

		return when (filter.sort!!.asc) {
			null -> { Sort.by(Sort.Direction.ASC, "name") }
			true -> { Sort.by(Sort.Direction.ASC, filter.sort!!.value) }
			false ->{ Sort.by(Sort.Direction.DESC, filter.sort!!.value )}
		}
	}

}