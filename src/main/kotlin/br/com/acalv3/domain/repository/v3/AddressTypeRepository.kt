package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.AddressTypeModel
import org.springframework.data.jpa.repository.JpaRepository

interface AddressTypeRepository : JpaRepository<AddressTypeModel, Long>{
    fun findByName(name: String): AddressTypeModel
}