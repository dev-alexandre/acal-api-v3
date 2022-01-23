package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.dto.FilterDTO
import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.repository.v3.ContractRepository
import br.com.acalv3.domain.service.AppService
import br.com.acalv3.domain.spec.ContractSpec
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ContractService(
	val repository: ContractRepository,
): AppService<ContractModel>(repository, repository) {

    override fun findByName(name: String): ContractModel =
        repository.findByName(name)

	override fun prepareForSave(u: ContractModel) {
		super.prepareForSave(u)

		val year = LocalDateTime.now().year.toString().padStart(4,'0')
		val group = u.group?.id?.toString()?.padStart(4,'0')
		val count = count().toString().padStart(6,'0')

		u.name = "${year}${group}${count}"
	}

	override fun pageable(filter: FilterDTO<ContractModel>): Page<ContractModel> {
		return repository.findAll(

			ContractSpec(
				filter.model
			),

			getPage(filter)
		)
	}

}
