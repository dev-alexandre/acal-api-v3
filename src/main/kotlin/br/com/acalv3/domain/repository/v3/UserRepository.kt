package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserModel, Long> {
	fun findByUserName(name: String): UserModel
}