package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.ContractModel
import br.com.acalv3.domain.repository.v3.ContractRepository
import br.com.acalv3.domain.service.AppService
import org.springframework.stereotype.Service

@Service
class ContractService(
	val repository: ContractRepository,
): AppService<ContractModel>(repository) {

    override fun findByName(name: String): ContractModel =
        repository.findByName(name)

}