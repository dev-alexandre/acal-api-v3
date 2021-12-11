package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "address_type_model")
data class AddressTypeModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_address_type`"
    )
    var id: Long? = null,

    @Column(unique = true, name = "uq_address_name")
    var name: String? = "",

) : AbstractModel()