package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.GroupModel
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<GroupModel, Long> {
    fun findByName(name: String): GroupModel
}