package br.com.acalv3.domain.model.v3

import br.com.acalv3.domain.model.AbstractModel
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "user_model")
data class UserModel(

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "`seq_user`"
    )
    var id: Long? = null,

    val userName: String = "",

    val password: String = "",

): AbstractModel()