package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.ContractModel
import org.springframework.data.jpa.repository.JpaRepository

interface ContractRepository : JpaRepository<ContractModel, Long>{
    fun findByName(name: String): ContractModel
}