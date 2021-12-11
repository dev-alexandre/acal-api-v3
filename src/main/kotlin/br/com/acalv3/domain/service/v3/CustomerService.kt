package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.repository.v3.CustomerRepository
import br.com.acalv3.domain.service.AppService
import org.springframework.stereotype.Service

@Service
class CustomerService(
	val repository: CustomerRepository,
): AppService<CustomerModel>(repository) {

    override fun findByName(name: String): CustomerModel =
        repository.findByName(name)
}