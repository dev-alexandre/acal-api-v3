package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "group_model")
data class GroupModel (

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_group`"
    )
    var id: Long? = null,

    @Column(unique = true)
    var name: String? = "",

    var monetaryValue: Double? = 0.00,

    @ManyToOne(cascade = [CascadeType.MERGE])
    var category: CategoryModel? = null,

    ) : AbstractModel()