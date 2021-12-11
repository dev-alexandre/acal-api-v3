package br.com.acalv3.domain.repository.v3

import br.com.acalv3.domain.model.v3.PlaceModel
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<PlaceModel, Long>