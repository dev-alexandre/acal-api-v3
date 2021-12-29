package br.com.acalv3.domain.service.v3

import br.com.acalv3.domain.model.v3.PlaceModel
import br.com.acalv3.domain.repository.v3.PlaceRepository
import br.com.acalv3.domain.service.AppService
import org.springframework.stereotype.Service

@Service
class PlaceService(
	val repository: PlaceRepository,
): AppService<PlaceModel>(repository) {

    override fun findByName(name: String): PlaceModel {
        throw RuntimeException("is not possible")
    }

}