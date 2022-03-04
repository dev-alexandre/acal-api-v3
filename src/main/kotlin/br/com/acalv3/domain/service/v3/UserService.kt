package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.UserModel
import br.com.acalv3.domain.repository.v3.UserRepository
import br.com.acalv3.domain.service.AppService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.testng.util.Strings

@Service
class UserService (
    private val userRepository: UserRepository
    ) : AppService<UserModel>(userRepository, userRepository), UserDetailsService {
	private var logger: Logger = LoggerFactory.getLogger(AppService::class.java)

	override fun findByName(name: String): UserModel =
		userRepository.findByUsername(name)?: throw UsernameNotFoundException("")

	override fun loadUserByUsername(username: String): UserDetails {
		return userRepository.findByUsername(username) ?: throw UsernameNotFoundException("")
	}

	fun registerUser(userModel: UserModel) {

		if (Strings.isNullOrEmpty(userModel.name)) {
			logger.info("Nome é paramêtro obrigatório")
			throw RuntimeException("Nome é paramêtro obrigatório")
		}

		if(userModel.password.isEmpty()) {
			logger.info("Senha é paramêtro obrigatório")
			throw RuntimeException("Senha é paramêtro obrigatório")
		}

		BCryptPasswordEncoder().encode(userModel.password).also { userModel.password = it }
		super.prepareForSave(userModel)
		userRepository.save(userModel)
	}

}
