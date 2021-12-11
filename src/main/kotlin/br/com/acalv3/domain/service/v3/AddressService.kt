package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.AddressModel
import br.com.acalv3.domain.repository.v3.AddressRepository
import br.com.acalv3.domain.service.AppService
import org.springframework.stereotype.Service

@Service
class AddressService(
	val repository: AddressRepository,
): AppService<AddressModel>(repository) {

    override fun findByName(name: String): AddressModel =
        repository.findByName(name)

}