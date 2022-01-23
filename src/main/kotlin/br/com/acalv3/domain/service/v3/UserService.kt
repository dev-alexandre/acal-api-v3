package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.UserModel
import br.com.acalv3.domain.repository.v3.UserRepository
import br.com.acalv3.domain.service.AppService

class UserService (
    private val userRepository: UserRepository
    ) : AppService<UserModel>(userRepository, userRepository) {

	override fun findByName(name: String): UserModel =
		userRepository.findByUserName(name)

}
