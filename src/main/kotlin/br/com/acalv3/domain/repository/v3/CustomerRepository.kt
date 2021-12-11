package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerModel, Long>{
    fun findByName(name: String): CustomerModel
}