package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.exception.RequiredFieldException
import br.com.acalv3.domain.model.v3.GroupModel
import br.com.acalv3.domain.repository.v3.GroupRepository
import br.com.acalv3.domain.service.AppService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class GroupService(
	val repository: GroupRepository,
): AppService<GroupModel>(repository) {

	override fun findByName(@PathVariable name: String): GroupModel =
		repository.findByName(name)

	override fun valid(u: GroupModel) {
		if(u.category == null){
			throw RequiredFieldException("Categoria é um campo obrigatório")
		}
	}
}