package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "category_model")
data class CategoryModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_category`"
    )
    var id: Long? = null,

    var name: String? = "",

) : AbstractModel()