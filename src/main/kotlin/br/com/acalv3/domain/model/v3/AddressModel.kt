package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "address_model")
data class AddressModel (

	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_address`"
    )
    var id: Long? = null,

	@ManyToOne(cascade = [CascadeType.PERSIST])
    var addressType: AddressTypeModel? = null,

	@Column(unique = true)
    var name: String? = "",

) : AbstractModel()