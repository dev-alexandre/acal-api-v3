package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "place_model")
data class PlaceModel (

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_place`"
    )
    var id: Long? = null,

	@ManyToOne(cascade = [CascadeType.MERGE])
    var address: AddressModel? = null,

	var number: String? = "",

	var letter: String? = "",

	) : AbstractModel()